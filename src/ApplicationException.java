public class ApplicationException extends Exception{
    public ApplicationException(String message){
        super(message);

        //men her shert ucun ayrin custom exception adlari yaratmaliyam? men checked custom exception secdim
        // biz custom serilizasiya ist.edeceyik?
    }
}
