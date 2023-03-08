package com.products.controllers;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.products.model.Catalog;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.products.model.Product;
import com.products.services.ProductService;
import com.products.services.FileStorageService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

@Tag(name = "Products", description = "Endpoints for managing products")
@RestController
@RequestMapping("/product")
class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService service;

    @Autowired
    private FileStorageService fileStorageService;

    @Operation(summary = "Get Local Catalog of Products")
    @GetMapping("/catalog")
    public Iterable<Catalog> getCatalog() {
        return service.getCatalog();
    }

    @Operation(summary = "Gets a specific Local product by its SKU")
    @GetMapping("/productBySku/{sku}")
    public ResponseEntity<Product> getBySkuLocal(@PathVariable("sku") final String sku) {
        final Product product = service.getBySkuLocal(sku);
        return ResponseEntity.ok().body(product);
    }

    @Operation(summary = "Gets a specific product by its designation or SKU")
    @GetMapping(value = "/search")
    public Page<Product> getBySkuOrDesignation(@RequestParam("skuOrDesignation") final String skuOrDesignation, @RequestParam("offset") final int offset, @RequestParam("pageSize") final int pageSize) throws IOException, InterruptedException {
        return service.getBySkuOrDesignation(skuOrDesignation,offset,pageSize);

    }

    @Operation(summary = "Uploads a photo of a product")
    @PostMapping("/{sku}/photo")
    @ResponseStatus(HttpStatus.CREATED)
    public UploadFileResponse uploadFile(@PathVariable("sku") final String sku,
                                         @RequestParam("file") final MultipartFile file) {

        final String fileName = fileStorageService.storeFile(sku, file);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentRequestUri().pathSegment(fileName)
                .toUriString();
        fileDownloadUri = fileDownloadUri.replace("/photos/", "/photo/");

        // TODO save info of the file on the database

        return new UploadFileResponse(fileName, fileDownloadUri, file.getContentType(), file.getSize());
    }


    @Operation(summary = "Uploads a set of photos of a product")
    @PostMapping("/{sku}/photos")
    @ResponseStatus(HttpStatus.CREATED)
    public List<UploadFileResponse> uploadMultipleFiles(@PathVariable("sku") final String sku,
                                                        @RequestParam("files") final MultipartFile[] files) {
        return Arrays.asList(files).stream().map(f -> uploadFile(sku, f)).collect(Collectors.toList());
    }


    @Operation(summary = "Shows a photo of a product based on its name")
    @GetMapping("/{sku}/photo/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable final String fileName,
                                                 final HttpServletRequest request) {
        // Load file as Resource
        final Resource resource = fileStorageService.loadFileAsResource(fileName+".jpg");

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (final IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok().contentType(MediaType.parseMediaType(contentType))
                //Outra alternativa
               // .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    @Operation(summary = "Generates Bar Code image based on its sku")
    @GetMapping(value = "/{sku}/barcode", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueEAN13Barcode(@PathVariable("sku")final String sku) {
        return ResponseEntity.ok(service.generateEAN13BarcodeImage(sku));
    }

    @Operation(summary = "Generates Bar Code image based on its sku in case of sku having letters")
    @GetMapping(value = "/{sku}/barcode128", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<BufferedImage> barbecueCode128Barcode(@PathVariable("sku") final String sku) throws Exception {
        return ResponseEntity.ok(service.generateCode128BarcodeImage(sku));
    }


    @Operation(summary = "Verifies if product exists based on its sku")
    @GetMapping("/{sku}/productExistence")
    public boolean doesProductExist(@PathVariable("sku")final String sku){
        return service.productExistence(sku);
    }


    @Operation(summary = "Gets a specific product by its designation or SKU SEM PAGE")
    @GetMapping(value = "/searchSemPage/{skuOrDesignation}")
    public List<Product> getBySkuOrDesignationSemPage(@PathVariable("skuOrDesignation") final String skuOrDesignation) {
        return service.getBySkuOrDesignationSemPage(skuOrDesignation);

    }

}


