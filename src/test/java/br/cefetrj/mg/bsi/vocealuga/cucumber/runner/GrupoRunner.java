package br.cefetrj.mg.bsi.vocealuga.cucumber.runner;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="classpath:", stepNotifications = true, glue="br.cefetrj.mg.bsi.vocealuga.cucumber.step", monochrome=true, dryRun=false,tags="@GerenciarGrupos",
plugin = { "json:target/cucumber.json", "pretty","html:target/cucumber-reports.html" }

		)
public class GrupoRunner {

}
