package br.cefetrj.mg.bsi.vocealuga.faker;

import java.util.List;
import java.util.Locale;


import com.github.javafaker.Faker;

public abstract class BaseFaker<E> {
    protected Faker faker = new Faker(new Locale("pt", "BR"));
    public abstract E create();
    public abstract List<E> create(int amount);
    public abstract String customFaker(int amount);
}
