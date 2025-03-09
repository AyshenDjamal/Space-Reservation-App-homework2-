public class ApplicationException extends Exception{
    //private String errorCode;


    public ApplicationException(String message){
        super(message);
        //this.errorCode = errorCode;

    }

    /*public String getErrorCode() {
        return errorCode;
    }

    public String getDetailedMessage(){
        return "Error " + ": " + getMessage();
    }*/
}
