package br.com.casadocodigo.loja.conf;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import br.com.casadocodigo.loja.controllers.HomeController;
import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.infra.FileSaver;

@EnableWebMvc
@ComponentScan(basePackageClasses= {HomeController.class, ProdutoDAO.class,FileSaver.class})
public class AppWebConfiguration {
	
	//BEAN CRIADO PARA MOSTRAR O CAMINHO ONDE TODAS AS PAGINAS SERAM COLOCADAS//
	//SUFFIX PARA DIZER AO SPRING QUE TODA PAGINA SERA.JSP//
	@Bean
	public InternalResourceViewResolver internalResourceViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix( ".jsp");
		return resolver;
	}
	
	//BEAN RESPONSAVEL PARA CRIAR ENVIAR AS MENSAGENS PARA AS VIEWS//
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setCacheSeconds(1);
		return messageSource;
	}
	
	//BEAN PARA CONVERTER AS DATAS NO ESTILO BRASILEIRO dia/mes/ano//
	@Bean
	public FormattingConversionService mvcConversionService() {
	    DefaultFormattingConversionService conversionService = 
	        new DefaultFormattingConversionService();
	    DateFormatterRegistrar registrar = new DateFormatterRegistrar();
	    registrar.setFormatter(new DateFormatter("dd/MM/yyyy"));
	    registrar.registerFormatters(conversionService);

	    return conversionService;
	}
	
	@Bean
	public MultipartResolver multipartResolver () {
		return new StandardServletMultipartResolver();
	}

}
