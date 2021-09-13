package proyecto2.mtsolutions.exceptions;

public class ServiceException extends RuntimeException {
    private static final String DEFAULT_MESSAGE = "Algo ha salido mal";
    private ErrorCode errorCode;
    public ServiceException() {
        super(DEFAULT_MESSAGE);
        this.errorCode = ErrorCode.INTERNAL_ERROR;
    }
    public ServiceException(String message) {
        super(message);
        this.errorCode = ErrorCode.CUSTOM_ERROR;
    }
    public ServiceException(ErrorCode errorCode) {
        super(DEFAULT_MESSAGE);
        this.errorCode = errorCode;
    }
    public ServiceException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
    public String toJSON(){
        JSONError je = new JSONError();
        je.setMensaje(this.getMessage() == null ? "" : this.getMessage());
        return null;
    }
    public ErrorCode getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
    static final class JSONError {
        private String mensaje;
        public String getMensaje() {
            return mensaje;
        }
        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }

}
