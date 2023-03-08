package com.products.services;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.products.model.Catalog;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.upcean.EAN13Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.products.model.Product;
import com.products.repositories.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    private HttpRequestHelper helper=new HttpRequestHelper();

    private static final Font BARCODE_TEXT_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 14);


    @Override
    public List<Catalog> getCatalog() {
        return repository.getCatalog();
    }


    @Override
    public Product getBySkuLocal(String sku) {
        Optional<Product> productOptional=repository.findById(sku);
        if (productOptional.isPresent())
            return productOptional.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Product Not Found");
    }


    @Override
    public Page<Product> getBySkuOrDesignation(String skuOrDesignation,int offset,int pageSize) throws IOException, InterruptedException {
            return repository.getBySkuOrDesignation(skuOrDesignation,PageRequest.of(offset,pageSize));
    }



    @Override
    public BufferedImage generateEAN13BarcodeImage(String barcodeText) {
        EAN13Bean barcodeGenerator = new EAN13Bean();
        BitmapCanvasProvider canvas =
                new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        barcodeGenerator.generateBarcode(canvas, barcodeText);
        return canvas.getBufferedImage();
    }

    @Override
    public BufferedImage generateCode128BarcodeImage(String barcodeText) {
        Code128Bean barcodeGenerator = new Code128Bean();
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(160, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        barcodeGenerator.generateBarcode(canvas, barcodeText);
        return canvas.getBufferedImage();
    }

    @Override
    public boolean productExistence(String sku) {
        Optional<Product> productOptional=repository.findById(sku);
        boolean isPresent=productOptional.isPresent();
        return isPresent;
    }

    @Override
    public List<Product> getBySkuOrDesignationSemPage(String skuOrDesignation) {
        return repository.getBySkuOrDesignationSemPage(skuOrDesignation);
    }



}
