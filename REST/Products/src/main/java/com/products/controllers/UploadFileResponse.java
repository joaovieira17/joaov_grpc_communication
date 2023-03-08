package com.products.controllers;

/**
 * <p>
 * code based on
 * https://github.com/callicoder/spring-boot-file-upload-download-rest-api-example
 *
 *
 */
public class UploadFileResponse {
    private final String fileName;
    private final String fileDownloadUri;
    private final String fileType;
    private final long size;

    public UploadFileResponse(final String fileName, final String fileDownloadUri, final String fileType,
                              final long size) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public long getSize() {
        return size;
    }
}
