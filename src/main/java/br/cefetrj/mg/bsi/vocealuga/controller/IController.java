package br.cefetrj.mg.bsi.vocealuga.controller;

import org.springframework.ui.Model;

public interface IController<k> {
	public String index(Model model);
	public String edit(int id, Model model);
	public String create(Model model);
	public String store( k objeto, Model model);
	public String update(int id, Model model);
	public String delete( int id, Model model);
}
