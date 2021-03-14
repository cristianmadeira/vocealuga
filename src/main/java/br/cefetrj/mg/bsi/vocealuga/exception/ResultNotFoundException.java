package br.cefetrj.mg.bsi.vocealuga.exception;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getResultNotFoundMessage;

public class ResultNotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -9060796266147167258L;

    public ResultNotFoundException() {
        super();
    }

    public ResultNotFoundException(String fieldName) {
        super(getResultNotFoundMessage(fieldName));
    }
}
