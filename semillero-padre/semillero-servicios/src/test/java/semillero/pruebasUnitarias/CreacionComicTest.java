package semillero.pruebasUnitarias;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hbt.semillero.dto.ComicDTO;
import com.hbt.semillero.enums.EstadoEnum;
import com.hbt.semillero.enums.TematicaEnum;

/**
 * 
 * <b>Descripción:<b> Clase que determina los tests correspondientes a la entidad Comic
 *  
 * @author cataclas
 * @version
 */
public class CreacionComicTest {

	/**
	 * Constante que contendra el log de la clase Comic
	 */
	private final static Logger Log = Logger.getLogger(CreacionComicTest.class);
	private ArrayList<ComicDTO> ComicList;

	@BeforeTest
	public void inicializar() {
		BasicConfigurator.configure(); //Inicializa la ejecución del test
		Log.info("---------------------------INICIAN PRUEBAS UNITARIAS-----------------------");
	}
	
	/**
	 * 
	 * Metodo encargado de crear comics a partir del constructor
	 * 
	 * @author cataclas
	 *
	 */
	@BeforeTest
	public void crearComicDTO() {
		ComicList = new ArrayList<>();
   		
		//Crea un objeto de tipo ComicDTO, basado en el constructor
		ComicDTO comicDTO1 = new ComicDTO(11L, "Dragon Ball Yamcha1", "Planeta Cómic1", TematicaEnum.FANTASTICO,
		"Manga Shonen1", 102, new BigDecimal(1100), "Dragon Garow Lee1", Boolean.FALSE, LocalDate.now(), EstadoEnum.INACTIVO,
		1L);
	
		//Crea un objeto de tipo ComicDTO, basado en el constructor
		ComicDTO comicDTO2 = new ComicDTO(21L, "Dragon Ball Yamcha2", "Planeta Cómic2", TematicaEnum.BELICO,
		"Manga Shonen2", 202, new BigDecimal(2100), "Dragon Garow Lee2", Boolean.TRUE, LocalDate.now(), EstadoEnum.ACTIVO,
		2L);
	
	    //Crea un objeto de tipo ComicDTO, basado en el constructor
	    ComicDTO comicDTO3 = new ComicDTO(31L, "Dragon Ball Yamcha3", "Planeta Cómic3", TematicaEnum.AVENTURAS,
	    "Manga Shonen3", 302, new BigDecimal(3100), "Dragon Garow Lee3", Boolean.TRUE, LocalDate.now(), EstadoEnum.ACTIVO,
	    3L);
	
	    //Crea un objeto de tipo ComicDTO, basado en el constructor
	    ComicDTO comicDTO4 = new ComicDTO(41L, "Dragon Ball Yamcha4", "Planeta Cómic4", TematicaEnum.CIENCIA_FICCION,
	    "Manga Shonen4", 402, new BigDecimal(4100), "Dragon Garow Lee4", Boolean.TRUE, LocalDate.now(), EstadoEnum.INACTIVO,
	    4L);
	
	    //Crea un objeto de tipo ComicDTO, basado en el constructor
	    ComicDTO comicDTO5 = new ComicDTO(51L, "Dragon Ball Yamcha5", "Planeta Cómic5", TematicaEnum.AVENTURAS,
	    "Manga Shonen5", 502, new BigDecimal(5100), "Dragon Garow Lee5", Boolean.TRUE, LocalDate.now(), EstadoEnum.ACTIVO,
	    5L);
	
	    //Crea un objeto de tipo ComicDTO, basado en el constructor
	    ComicDTO comicDTO6 = new ComicDTO(61L, "Dragon Ball Yamcha6", "Planeta Cómic6", TematicaEnum.HISTORICO,
	    "Manga Shonen6", 602, new BigDecimal(6100), "Dragon Garow Lee6", Boolean.TRUE, LocalDate.now(), EstadoEnum.INACTIVO,
	    6L);
	
	    //Crea un objeto de tipo ComicDTO, basado en el constructor
	    ComicDTO comicDTO7 = new ComicDTO(71L, "Dragon Ball Yamcha7", "Planeta Cómic7", TematicaEnum.BELICO,
	    "Manga Shonen7", 702, new BigDecimal(7100), "Dragon Garow Lee7", Boolean.TRUE, LocalDate.now(), EstadoEnum.ACTIVO,
	    7L);
	
	    //Crea un objeto de tipo ComicDTO, basado en el constructor
	    ComicDTO comicDTO8 = new ComicDTO(81L, "Dragon Ball Yamcha8", "Planeta Cómic8", TematicaEnum.DEPORTIVO,
	    "Manga Shonen8", 802, new BigDecimal(8100), "Dragon Garow Lee8", Boolean.TRUE, LocalDate.now(), EstadoEnum.ACTIVO,
	    8L);
	
	    //Crea un objeto de tipo ComicDTO, basado en el constructor
	    ComicDTO comicDTO9 = new ComicDTO(91L, "Dragon Ball Yamcha9", "Planeta Cómic9", TematicaEnum.AVENTURAS,
	    "Manga Shonen9", 902, new BigDecimal(9100), "Dragon Garow Lee9", Boolean.TRUE, LocalDate.now(), EstadoEnum.ACTIVO,
	    9L);
	
	    //Crea un objeto de tipo ComicDTO, basado en el constructor
	    ComicDTO comicDTO10 = new ComicDTO(101L, "Dragon Ball Yamcha10", "Planeta Cómic10", TematicaEnum.CIENCIA_FICCION,
	    "Manga Shonen10", 1002, new BigDecimal(10100), "Dragon Garow Lee10", Boolean.TRUE, LocalDate.now(), EstadoEnum.INACTIVO,
	    10L);

		ComicList.add(comicDTO1);
		ComicList.add(comicDTO2);
		ComicList.add(comicDTO3);
		ComicList.add(comicDTO4);
		ComicList.add(comicDTO5);
		ComicList.add(comicDTO6);
		ComicList.add(comicDTO7);
		ComicList.add(comicDTO8);
		ComicList.add(comicDTO9);
		ComicList.add(comicDTO10);
	}
		
	/**
	 * 
	 * Metodo encargado de validar los comics que presentan estado "Activo"
	 * 
	 * @author cataclas
	 *
	 */
	@Test
	public void validarComicEstadoActivo() {
		Log.info("inicia ejecución del metodo validarComicEstadoActivo()");
		ArrayList<ComicDTO> activos = new ArrayList<>();
		
        for (int i = 0; i < ComicList.size(); i++) {  
        	if(ComicList.get(i).getEstadoEnum() == EstadoEnum.ACTIVO) {        		
        		Assert.assertEquals(ComicList.get(i).getEstadoEnum(), EstadoEnum.ACTIVO); 	
        		Log.info(ComicList.get(i).getEstadoEnum() + " - " + ComicList.get(i).getNombre());
        		activos.add(ComicList.get(i));
        		System.out.println(ComicList.get(i).toString());
        	}
        }

        //System.out.println("lista activos");
        //System.out.println(activos.toString());
		Log.info("Finaliza la ejecución del metodo validarComicEstadoActivo()");
	}	
	
	
	/**
	 * 
	 * Metodo encargado de validar los comics que presentan estado "Inactivo"
	 * 
	 * @author cataclas
	 *
	 */
	@Test
	public void validarComicEstadoInactivo() {
		Log.info("inicia ejecución del metodo validarComicEstadoInactivo()");
		ArrayList<String> inactivos = new ArrayList<>();
		
		try {
	        for (int i = 0; i < ComicList.size(); i++) {  
	        	if(ComicList.get(i).getEstadoEnum() == EstadoEnum.INACTIVO) {        		
	        		Assert.assertEquals(ComicList.get(i).getEstadoEnum(), EstadoEnum.INACTIVO); 	
	        		Log.info(ComicList.get(i).getEstadoEnum() + " - " + ComicList.get(i).getNombre());
	          		inactivos.add(ComicList.get(i).getNombre());
	        	} else if(ComicList.get(i).getEstadoEnum() == EstadoEnum.ACTIVO) {
					Log.info("Se ha generado un error funcional probando el test validarComicEstadoInactivo()");
					throw new Exception("El estado no coincide");
				}
	        }
			
		}
		catch (Exception e){
			Assert.assertEquals(e.getMessage(), "Se ha detectado que de " + ComicList.size() + " comics se encontraron que " + (ComicList.size() - inactivos.size()) + " se encuentran activos y " + inactivos.size() + " inactivos. Los comics inactivos son: " + inactivos);
		}
		
		Log.info("Finaliza la ejecución del metodo validarComicEstadoInactivo()");
	}	
	
	@AfterTest
	public void finalizar() {
		BasicConfigurator.configure(); //Finaliza la ejecución del test
		Log.info("---------------------------FINALIZAN PRUEBAS UNITARIAS-----------------------");
	}
}
