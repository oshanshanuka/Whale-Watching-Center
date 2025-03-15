package lk.ijse.whalewatchingcenter.exception;

public class ImageExtractionFailedException extends RuntimeException {
    public ImageExtractionFailedException(String imageId) {
        super("Failed to extract image with id: " + imageId);
    }
}
