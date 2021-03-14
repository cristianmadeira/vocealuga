package br.cefetrj.mg.bsi.vocealuga.exception;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getInvalidIdMessage;

public class InvalidIdException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public InvalidIdException() {
        super();

    }

    public InvalidIdException(int id) {
        super(getInvalidIdMessage(id));
    }
}
