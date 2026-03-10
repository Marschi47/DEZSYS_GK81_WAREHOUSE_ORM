package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private DataWarehouseRepository warehouseRepository;

    @PostMapping("/setup")
    public String setupData() {
        // Warehouse 1
        DataWarehouse w1 = new DataWarehouse();
        w1.setWarehouseID("001");
        w1.setWarehouseName("Linz Bahnhof");
        w1.setWarehouseAddress("Bahnhofsstrasse 27/9");
        w1.setWarehousePostalCode("4020");
        w1.setWarehouseCity("Linz");
        w1.setWarehouseCountry("Austria");
        w1.setTimestamp(LocalDateTime.now().toString());

        Product p1 = new Product(); p1.setProductID("00-443175"); p1.setProductName("Bio Orangensaft Sonne"); p1.setProductCategory("Getraenk"); p1.setProductQuantity(2500); p1.setProductUnit("Packung 1L"); p1.setWarehouse(w1);
        Product p2 = new Product(); p2.setProductID("00-871895"); p2.setProductName("Bio Apfelsaft Gold"); p2.setProductCategory("Getraenk"); p2.setProductQuantity(3420); p2.setProductUnit("Packung 1L"); p2.setWarehouse(w1);
        Product p3 = new Product(); p3.setProductID("00-111111"); p3.setProductName("Cola"); p3.setProductCategory("Getraenk"); p3.setProductQuantity(1000); p3.setProductUnit("Dose 0.33L"); p3.setWarehouse(w1);
        Product p4 = new Product(); p4.setProductID("00-222222"); p4.setProductName("Fanta"); p4.setProductCategory("Getraenk"); p4.setProductQuantity(500); p4.setProductUnit("Dose 0.33L"); p4.setWarehouse(w1);
        Product p5 = new Product(); p5.setProductID("00-333333"); p5.setProductName("Wasser"); p5.setProductCategory("Getraenk"); p5.setProductQuantity(5000); p5.setProductUnit("Flasche 1.5L"); p5.setWarehouse(w1);
        
        w1.setProductData(Arrays.asList(p1, p2, p3, p4, p5));
        warehouseRepository.save(w1);

        // Warehouse 2
        DataWarehouse w2 = new DataWarehouse();
        w2.setWarehouseID("002");
        w2.setWarehouseName("Wien Zentrum");
        w2.setWarehouseAddress("Stephansplatz 1");
        w2.setWarehousePostalCode("1010");
        w2.setWarehouseCity("Wien");
        w2.setWarehouseCountry("Austria");
        w2.setTimestamp(LocalDateTime.now().toString());

        Product p6 = new Product(); p6.setProductID("01-443175"); p6.setProductName("Brot"); p6.setProductCategory("Essen"); p6.setProductQuantity(150); p6.setProductUnit("Stueck"); p6.setWarehouse(w2);
        Product p7 = new Product(); p7.setProductID("01-871895"); p7.setProductName("Apfel"); p7.setProductCategory("Essen"); p7.setProductQuantity(300); p7.setProductUnit("KG"); p7.setWarehouse(w2);
        Product p8 = new Product(); p8.setProductID("01-111111"); p8.setProductName("Banane"); p8.setProductCategory("Essen"); p8.setProductQuantity(200); p8.setProductUnit("KG"); p8.setWarehouse(w2);
        Product p9 = new Product(); p9.setProductID("01-222222"); p9.setProductName("Schokolade"); p9.setProductCategory("Essen"); p9.setProductQuantity(400); p9.setProductUnit("Tafel"); p9.setWarehouse(w2);
        Product p10 = new Product(); p10.setProductID("01-333333"); p10.setProductName("Chips"); p10.setProductCategory("Essen"); p10.setProductQuantity(600); p10.setProductUnit("Packung"); p10.setWarehouse(w2);

        w2.setProductData(Arrays.asList(p6, p7, p8, p9, p10));
        warehouseRepository.save(w2);

        return "Successfully inserted 2 Warehouses and 10 Products!";
    }

    @GetMapping("/all")
    public Iterable<DataWarehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }
}