package br.cefetrj.mg.bsi.vocealuga.exception;

import static br.cefetrj.mg.bsi.vocealuga.utils.MessageUtils.getEmptyListMessage;

public class EmptyListException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EmptyListException() {
        super();

    }

    public EmptyListException(String str) {
        super(getEmptyListMessage(str));
    }
}
