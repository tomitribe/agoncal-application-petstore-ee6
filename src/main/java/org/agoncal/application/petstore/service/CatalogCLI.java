/* =====================================================================
 *
 * Copyright (c) 2011 David Blevins.  All rights reserved.
 *
 * =====================================================================
 */
package org.agoncal.application.petstore.service;

import org.agoncal.application.petstore.domain.Category;
import org.agoncal.application.petstore.domain.Item;
import org.agoncal.application.petstore.domain.Product;
import org.tomitribe.crest.api.Command;
import org.tomitribe.crest.api.Default;
import org.tomitribe.crest.api.Option;
import org.tomitribe.telnet.api.TelnetListener;
import org.tomitribe.util.PrintString;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@MessageDriven
public class CatalogCLI implements TelnetListener {

    @EJB
    public CatalogService catalogService;

    @Command
    public String items() {
        final List<Item> items = new ArrayList<Item>(catalogService.findAllItems());

        return asTable(items);
    }

    @Command
    public String items(String keyword) {
        final List<Item> items = new ArrayList<Item>(catalogService.searchItems(keyword));

        return asTable(items);
    }

    @Command
    public void removeItem(Long id) {
        catalogService.removeItem(id);
    }

    @Command
    public String createItem(
            @Option("product") String productName,
            @Option("description") @Default("A new item") String description,
            @Option("image") @Default("fish1.jpg") String image,
            @Option("price") @Default("12.0") float price,
            String name) {

        final List<Product> products = catalogService.findProductsByName(productName);
        if (products.size() == 0) {
            return "No such Product: " + productName;
        }

        final Product product = products.get(0);
        final Item item = new Item(name, price, image, product, description);

        final Item added = catalogService.createItem(item);

        return asTable(Arrays.asList(added));
    }

    private String asTable(List<Item> items) {
        final PrintString out = new PrintString();

        final String format = " - %-10s %-30s %-10s %-20s %s %n";

        out.printf(format, "ID", "NAME", "COST", "PRODUCT", "CATEGORY");

        for (Item item : items) {
            final Product product = item.getProduct();
            final Category category = product.getCategory();

            out.printf(format, item.getId(), item.getName(), item.getUnitCost(), product.getName(), category.getName());
        }

        return out.toString();
    }
}
