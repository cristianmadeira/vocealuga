package br.cefetrj.mg.bsi.vocealuga.exception;
import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getDeleteExceptionMessage;

public class DeleteException extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public DeleteException(){
        super();
    }
    public DeleteException(String entityName, int size, String details){
        super(getDeleteExceptionMessage(entityName,size,details));
    }
}
