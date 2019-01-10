package examen.ord201701;

import java.io.*;
import java.sql.Connection;
import java.sql.Statement;
import java.util.*;


/** 
 * Clase de almac�n de farmacias de guardia (en un mapa de localidades)
 * 
 */
public class MapaFarmacias {
	
	private HashMap<String,ArrayList<FarmaciaGuardia>> mapaFarmacias = new HashMap<>();
	private int dia;
	private int mes;
	private Statement st;
	
	// Crea un mapa vac�o
	private MapaFarmacias() {
		dia = 0;
		mes = 0;
		mapaFarmacias = null;

	}
	
	/** Intenta cargar el mapa de farmacias del �ltimo fichero y lo devuelve
	 * @param ficheroFarmacias	Nombre de fichero de carga
	 * @throws NullPointerException	Si no ha sido posible cargarlo
	 */
	public MapaFarmacias( String ficheroFarmacias ) throws NullPointerException {
		// TAREA 2
		Connection con = BD.initBD("BDFarmacias.db");
		st = BD.usarCrearTablasBD(con);
		ArrayList<FarmaciaGuardia> farmacias = BD.farmaciaSelect(st); 
		for(FarmaciaGuardia f : farmacias) {
			if(mapaFarmacias.get(f.getZona()) == null){
				mapaFarmacias.put(f.getZona(), new ArrayList<>());
				mapaFarmacias.get(f.getZona()).add(f);
			} else
				mapaFarmacias.get(f.getZona()).add(f);
		}
//		throw new NullPointerException();  // TODO Actualmente no carga el mapa
	}

	/** Simula la carga del mapa de farmacias desde internet (en realidad, crea el mapa en local)
	 * @param codigoSimulacion	C�digo para simular la carga del mapa: 0=carga correcta, 1=no hay conexi�n
	 * @return	mapa de farmacias cargado, null si hay cualquier error en la carga (no hay conexi�n http, no se encuentra la web, el formato es incorrecto...)
	 */
	public static MapaFarmacias cargaMapaFarmaciasGuardia( int codigoSimulacion ) {
		if (codigoSimulacion!=0) return null;
		MapaFarmacias mapa = new MapaFarmacias();
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis( System.currentTimeMillis() );
		mapa.dia = gc.get( GregorianCalendar.DAY_OF_MONTH );
		mapa.mes = gc.get( GregorianCalendar.MONTH ) + 1;
		mapa.mapaFarmacias = new HashMap<String,ArrayList<FarmaciaGuardia>>();
		if (mapa.mapaFarmacias.get("Lekeitio")==null) mapa.mapaFarmacias.put( "Lekeitio", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Lekeitio" ).add( new FarmaciaGuardia( "Lekeitio", "09:00-09:00", "(Lekeitio)  Atea, N� 14  |  946843023" ) );
		if (mapa.mapaFarmacias.get("Uribe-Kosta")==null) mapa.mapaFarmacias.put( "Uribe-Kosta", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Uribe-Kosta" ).add( new FarmaciaGuardia( "Uribe-Kosta", "09:00-09:00", "(Sopela)  Gatzarri�e, N� 6 (Plaza Urgitxieta, Junto Al Metro De Larrabasterra)  |  94 4047600" ) );
		if (mapa.mapaFarmacias.get("Uribe-Kosta")==null) mapa.mapaFarmacias.put( "Uribe-Kosta", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Uribe-Kosta" ).add( new FarmaciaGuardia( "Uribe-Kosta", "09:00-22:00", "(Berango)  Sabino Arana, N� 20  |  94 6680360" ) );
		if (mapa.mapaFarmacias.get("Uribe-Kosta")==null) mapa.mapaFarmacias.put( "Uribe-Kosta", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Uribe-Kosta" ).add( new FarmaciaGuardia( "Uribe-Kosta", "09:00-22:00", "(Gorliz)  Itxas-Bide, N� 5  Gorliz  |  946775452" ) );
		if (mapa.mapaFarmacias.get("Uribe-Kosta")==null) mapa.mapaFarmacias.put( "Uribe-Kosta", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Uribe-Kosta" ).add( new FarmaciaGuardia( "Uribe-Kosta", "09:00-22:00", "(Plentzia)  Ribera, N� 20  Plentzia  |  946775301" ) );
		if (mapa.mapaFarmacias.get("Uribe-Kosta")==null) mapa.mapaFarmacias.put( "Uribe-Kosta", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Uribe-Kosta" ).add( new FarmaciaGuardia( "Uribe-Kosta", "09:00-22:00", "(Sopela)  Sabino Arana, 28  |  946129547" ) );
		if (mapa.mapaFarmacias.get("Uribe-Kosta")==null) mapa.mapaFarmacias.put( "Uribe-Kosta", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Uribe-Kosta" ).add( new FarmaciaGuardia( "Uribe-Kosta", "09:00-22:00", "(Sopela)  Loyola San Andres, N� 50 Larrabasterra  |  946762363" ) );
		if (mapa.mapaFarmacias.get("Uribe-Kosta")==null) mapa.mapaFarmacias.put( "Uribe-Kosta", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Uribe-Kosta" ).add( new FarmaciaGuardia( "Uribe-Kosta", "09:00-22:00", "(Sopela)  Iparraguirre, N� 3  |  94 6764768" ) );
		if (mapa.mapaFarmacias.get("Uribe-Kosta")==null) mapa.mapaFarmacias.put( "Uribe-Kosta", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Uribe-Kosta" ).add( new FarmaciaGuardia( "Uribe-Kosta", "09:00-22:00", "(Sopela)  Gatzarri�e, N� 6 (Plaza Urgitxieta, Junto Al Metro De Larrabasterra)  |  94 4047600" ) );
		if (mapa.mapaFarmacias.get("Uribe-Kosta")==null) mapa.mapaFarmacias.put( "Uribe-Kosta", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Uribe-Kosta" ).add( new FarmaciaGuardia( "Uribe-Kosta", "09:00-22:00", "(Urduliz)  Aita Gotzon, N� 9 B (Carretera General)  |  94 6764507" ) );
		if (mapa.mapaFarmacias.get("Barakaldo")==null) mapa.mapaFarmacias.put( "Barakaldo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Barakaldo" ).add( new FarmaciaGuardia( "Barakaldo", "09:00-22:00", "(Cruces)  Barrio Vista Alegre, N� 4 Cruces  |  94 4991829" ) );
		if (mapa.mapaFarmacias.get("Barakaldo")==null) mapa.mapaFarmacias.put( "Barakaldo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Barakaldo" ).add( new FarmaciaGuardia( "Barakaldo", "09:00-22:00", "(Cruces-Retuerto-Lutxana)  Pol�gono La Paz, Bl 1� Cruces  |  94 4994170" ) );
		if (mapa.mapaFarmacias.get("Barakaldo")==null) mapa.mapaFarmacias.put( "Barakaldo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Barakaldo" ).add( new FarmaciaGuardia( "Barakaldo", "09:00-22:00", "(Cruces-Retuerto-Lutxana)  Balejo, N� 5 (Frente Al Hospital) Cruces  |  94 4997842" ) );
		if (mapa.mapaFarmacias.get("Barakaldo")==null) mapa.mapaFarmacias.put( "Barakaldo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Barakaldo" ).add( new FarmaciaGuardia( "Barakaldo", "09:00-22:00", "(San Vicente)  Plaza Anteiglesia, 13 (San Vicente)  |  94 4372915" ) );
		if (mapa.mapaFarmacias.get("Barakaldo")==null) mapa.mapaFarmacias.put( "Barakaldo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Barakaldo" ).add( new FarmaciaGuardia( "Barakaldo", "09:00-22:00", "(San Vicente-Zuazo)  Arteagabeitia, N� 13  |  94 4994302" ) );
		if (mapa.mapaFarmacias.get("Barakaldo")==null) mapa.mapaFarmacias.put( "Barakaldo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Barakaldo" ).add( new FarmaciaGuardia( "Barakaldo", "09:00-22:00", "(San Vicente-Zuazo)  Autonom�a, N� 35 (Junto A Sanatorio San Eloy)  |  94 4375243" ) );
		if (mapa.mapaFarmacias.get("Barakaldo")==null) mapa.mapaFarmacias.put( "Barakaldo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Barakaldo" ).add( new FarmaciaGuardia( "Barakaldo", "09:00-22:00", "(San Vicente-Zuazo)  Ronda  Azkue, N� 38  |  94 4997820" ) );
		if (mapa.mapaFarmacias.get("Barakaldo")==null) mapa.mapaFarmacias.put( "Barakaldo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Barakaldo" ).add( new FarmaciaGuardia( "Barakaldo", "09:00-22:00", "(San Vicente-Zuazo)  Ronda Azkue, N� 4 (Frente Al Bec)  |  944851411" ) );
		if (mapa.mapaFarmacias.get("Barakaldo")==null) mapa.mapaFarmacias.put( "Barakaldo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Barakaldo" ).add( new FarmaciaGuardia( "Barakaldo", "09:00-22:00", "(San Vicente-Zuazo)  Unamuno, N� 4 (Final De Artegabeitia)  |  94 4901874" ) );
		if (mapa.mapaFarmacias.get("Barakaldo")==null) mapa.mapaFarmacias.put( "Barakaldo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Barakaldo" ).add( new FarmaciaGuardia( "Barakaldo", "09:00-22:00", "(Zaballa-Desierto)  Nafarroa, 28 - Perpendicular A Los Fueros- (Barrio Rontegui)  |  94 4373637" ) );
		if (mapa.mapaFarmacias.get("Barakaldo")==null) mapa.mapaFarmacias.put( "Barakaldo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Barakaldo" ).add( new FarmaciaGuardia( "Barakaldo", "09:00-22:00", "(Zaballa-Desierto)  Muguruza, N� 2 (Frente Al Ambulatorio)  |  94 4370765" ) );
		if (mapa.mapaFarmacias.get("Barakaldo")==null) mapa.mapaFarmacias.put( "Barakaldo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Barakaldo" ).add( new FarmaciaGuardia( "Barakaldo", "09:00-22:00", "(Zaballa-Desierto)  Avda. De La Libertad, N� 5 (Junto Al Monumento)  |  94 4372110" ) );
		if (mapa.mapaFarmacias.get("Barakaldo")==null) mapa.mapaFarmacias.put( "Barakaldo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Barakaldo" ).add( new FarmaciaGuardia( "Barakaldo", "22:00-09:00", "(Zaballa-Desierto)  Herriko Plaza, N� 14 (Junto A Supercor)  |  944372143" ) );
		if (mapa.mapaFarmacias.get("Ermua")==null) mapa.mapaFarmacias.put( "Ermua", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Ermua" ).add( new FarmaciaGuardia( "Ermua", "00:00-09:00", "(Eibar)  San Aguntin, 3  Eibar  |  943 820533" ) );
		if (mapa.mapaFarmacias.get("Ermua")==null) mapa.mapaFarmacias.put( "Ermua", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Ermua" ).add( new FarmaciaGuardia( "Ermua", "09:00-22:00", "(Ermua)  Zubiaurre, N� 9  |  943 170097" ) );
		if (mapa.mapaFarmacias.get("Ermua")==null) mapa.mapaFarmacias.put( "Ermua", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Ermua" ).add( new FarmaciaGuardia( "Ermua", "22:00-23:59", "(Ermua)  Zehar Kalea, N� 2  |  943 031703" ) );
		if (mapa.mapaFarmacias.get("Abanto-Muskiz")==null) mapa.mapaFarmacias.put( "Abanto-Muskiz", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Abanto-Muskiz" ).add( new FarmaciaGuardia( "Abanto-Muskiz", "09:00-22:00", "(Gallarta - Abanto)  Avda. Del Minero, N� 10 - Gallarta  |  946362559" ) );
		if (mapa.mapaFarmacias.get("Mungia")==null) mapa.mapaFarmacias.put( "Mungia", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Mungia" ).add( new FarmaciaGuardia( "Mungia", "09:00-22:00", "(Mungia)  Calle Butron, N� 6  |  94 6740344" ) );
		if (mapa.mapaFarmacias.get("Mungia")==null) mapa.mapaFarmacias.put( "Mungia", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Mungia" ).add( new FarmaciaGuardia( "Mungia", "09:00-22:00", "(Mungia)  Intxausti Eresgille, N� 10  |  94 6155648" ) );
		if (mapa.mapaFarmacias.get("Mungia")==null) mapa.mapaFarmacias.put( "Mungia", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Mungia" ).add( new FarmaciaGuardia( "Mungia", "09:00-22:00", "(Mungia)  Olerkari Lauaxeta, N� 12  |  94 6749149" ) );
		if (mapa.mapaFarmacias.get("Mungia")==null) mapa.mapaFarmacias.put( "Mungia", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Mungia" ).add( new FarmaciaGuardia( "Mungia", "09:00-22:00", "(Mungia)  Aita Elorriaga, N� 16  |  94 6156400" ) );
		if (mapa.mapaFarmacias.get("Mungia")==null) mapa.mapaFarmacias.put( "Mungia", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Mungia" ).add( new FarmaciaGuardia( "Mungia", "22:00-09:00", "(Mungia)  Alkartasuna Kalea, 8  |  946740233" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Abusu/La Pe�a-Zamakola)  Zam�cola, 57  |  94 4166347" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Albia)  Buenos Aires, 11  |  94 4231483" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Albia)  Col�n De Larre�tegui, 41  |  688988636" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Albia)  Ber�stegui, 1 (Salida Metro Abando En Ber�stegui)  |  94 4236143" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Albia)  Plaza San Jos�, S/N  |  94 4230860" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Albia)  Gran V�a, 8 (Frente A El Corte Ingles)  |  94 4233153" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Albia)  Alameda Mazarredo, 9 (Jardines Albia)  |  94 4232468" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Albia)  Henao, 14 (Trasera Plaza Del Ensanche)  |  94 4231748" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Ametzola)  Autonom�a, 9  |  94 4219061" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Ametzola)  Autonom�a, 41  |  94 4210501" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Ametzola)  Calle Doctor Land�n, 4 (La Casilla)  |  94 4271685" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Bego�a)  V�a Vieja De Lezama, 3 Zurbaran (Junto Al Batzoki)  |  94 4464227" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Campo Volantin)  Ur�barri, 3  |  94 4450548" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Deustu/Deusto)  Avda. Del Lehendakari Aguirre,1 (Rotonda Pte. Deusto)  |  94 4474269" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Deustu/Deusto)  Blas De Otero, 32 Deusto  |  94 4755256" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Deustu/Deusto)  Heliodoro De La Torre, 11 Deusto  |  94 4752871" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Deustu/Deusto)  Avda. Del Lehendakari Aguirre,30 Deusto  |  94 4750979" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Deustu/Deusto)  Julio Urquijo, 10 (Frente A San Felic�simo) Deusto  |  94 4751763" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Deustu/Deusto)  Rafaela De Ibarra, 35 Deusto  |  94 4754653" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Deustu/Deusto)  Avda. Madariaga, 35 (Frente Salida Metro) Deusto  |  94 4752519" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Diputazioa/Diputacion)  Alda. San Mam�s, 29  |  944210504" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Diputazioa/Diputacion)  Gardoqui, 9 (Frente A Iberdrola)  |  94 4166839" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Diputazioa/Diputacion)  Alameda Urquijo, 45 (Entre La Alhondiga Y La Plaza Indautxu)  |  94 4437992" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Diputazioa/Diputacion)  Hurtado De Amezaga, 10 (Frente Estaci�n Renfe)  |  94 4155716" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Diputazioa/Diputacion)  Navarra, 1 (Entre El Puente Del Arenal Y Plaza Circular)  |  944230035" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Diputazioa/Diputacion)  Licenciado Poza, 20  |  94 4411681" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Diputazioa/Diputacion)  Plaza Pedro Eguillor, 3 (Detr�s Del Hotel Carlton)  |  94 4795910" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Diputazioa/Diputacion)  Plaza Zab�lburu, 4  |  94 4439808" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Diputazioa/Diputacion)  Alda. Recalde, 58 (Esq. A Fern�ndez Del Campo)  |  944703686" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Diputazioa/Diputacion)  Rodr�guez Arias, 12 - Esquina Ercilla  |  94 4157202" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Diputazioa/Diputacion)  Alda. Recalde, 37 (Esq. Ldo. Poza)  |  944701212" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Diputazioa/Diputacion)  Alameda De Urquijo, 22 (Entre Elcano Y General Concha)  |  944210733" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Indautxu)  Licenciado Poza, 38  |  944412134" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Indautxu)  Alameda Urquijo, 60 (Plaza Indautxu)  |  944411447" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Indautxu)  Alameda Urquijo, 71  |  94 4417197" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Indautxu)  Gran V�a, 71  |  94 4412645" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Indautxu)  Rodr�guez Arias, 43 (Junto A La Plaza Campuzano)  |  94 4411026" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Indautxu)  Gran Via, 56 (Junto Cafeteria Toledo)  |  94 4424177" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Iralabarri-San Adrian)  Askatazuna, 7 (Miribilla)  |  94 6552556" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Iralabarri-San Adrian)  Irala, 32  |  94 4219637" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Iralabarri-San Adrian)  Juan De Garay, 33 San Adrian  |  94 4431535" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Iralabarri-San Adrian)  Avda. San Adri�n, 25 San Adrian  |  944218237" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Iralabarri-San Adrian)  Jardines De Gernika, 24 (Miribilla)  |  94 4155435" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Otxarkoaga)  Langaran, 10  Otxarkoaga  |  94 4732374" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Otxarkoaga)  Pau Casals, 1 Otxarkoaga  |  94 4124032" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(San Ignacio)  Benidorm, 13 (Salida Metro Sarriko) San Ignacio  |  94 4476787" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Santutxu)  J. Bol�bar Elorduy, 4 (Esq. El Carmelo)  |  94 4339539" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Santutxu)  Marcelino Men�ndez Pelayo, 26 (Frente Al Colegio Berriotxoa)  |  94 4337789" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Santutxu)  Fika, 65 (Esq. Men�ndez Y Pelayo)  |  944331038" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Santutxu)  Cocherito De Bilbao, G� Sagarm�naga, Bloq. 3  |  94 4112219" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Santutxu)  E. Ibarreta, 1 (Esq. Santa Clara 5-Continuaci�n Calle Carmelo)  |  94 4112781" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Txurdinaga)  Txomin Garat, 4 Txurdinaga  |  94 4126528" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Txurdinaga)  Txomin Garat, 9 Txurdinaga  |  94 4126673" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Zabala-San Francisco)  Zabala, 29  |  944161783" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Zabalburu)  Juan De Garay, 2 (Torres Zab�lburu) Zabalburu  |  94 4433549" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Zazpikaleak/Casco Viejo)  Plaza Nueva, 4  |  94 4154627" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Zazpikaleak/Casco Viejo)  Pl. Miguel De Unamuno, S/N  |  94 4154901" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Zazpikaleak/Casco Viejo)  Bidebarrieta, 9  |  94 4158563" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "09:00-22:00", "(Zazpikaleak/Casco Viejo)  Ronda - La Cruz, Casco Viejo  |  94 4152128" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "22:00-09:00", "(Indautxu)  Plaza Sagrado Coraz�n, 1 (Esq. Sabino Arana)  |  94 4415739" ) );
		if (mapa.mapaFarmacias.get("Bilbao")==null) mapa.mapaFarmacias.put( "Bilbao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bilbao" ).add( new FarmaciaGuardia( "Bilbao", "22:00-09:00", "(Txurdinaga)  Avda. Juli�n Gaiarre, 4 (Plaza De La Media Luna)  |  94 4128964" ) );
		if (mapa.mapaFarmacias.get("Abadi�o")==null) mapa.mapaFarmacias.put( "Abadi�o", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Abadi�o" ).add( new FarmaciaGuardia( "Abadi�o", "09:00-22:00", "(Abadi�o-Elorrio-Berriz)  Poligono Urbitarte, 4 P8  |  946212299" ) );
		if (mapa.mapaFarmacias.get("Leioa")==null) mapa.mapaFarmacias.put( "Leioa", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Leioa" ).add( new FarmaciaGuardia( "Leioa", "09:00-22:00", "(Artaza)  Artaza Kalea, N� 8 - (Neguri-Gane) -Junto Edificio Gobelas  |  94 4608501" ) );
		if (mapa.mapaFarmacias.get("Leioa")==null) mapa.mapaFarmacias.put( "Leioa", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Leioa" ).add( new FarmaciaGuardia( "Leioa", "09:00-22:00", "(Romo)  Maiatzaren Bata, N� 4 - Romo  |  94 4630066" ) );
		if (mapa.mapaFarmacias.get("Leioa")==null) mapa.mapaFarmacias.put( "Leioa", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Leioa" ).add( new FarmaciaGuardia( "Leioa", "09:00-22:00", "(Romo)  Larramendi Plaza, N� 2 - Romo  |  94 6572295" ) );
		if (mapa.mapaFarmacias.get("Encartaciones")==null) mapa.mapaFarmacias.put( "Encartaciones", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Encartaciones" ).add( new FarmaciaGuardia( "Encartaciones", "09:00-22:00", "(Balmaseda)  Correria, N� 8  |  94 6800233" ) );
		if (mapa.mapaFarmacias.get("Encartaciones")==null) mapa.mapaFarmacias.put( "Encartaciones", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Encartaciones" ).add( new FarmaciaGuardia( "Encartaciones", "09:00-22:00", "(Gordexola)  Plaza Molinar, N� 4  |  94 6799267" ) );
		if (mapa.mapaFarmacias.get("Encartaciones")==null) mapa.mapaFarmacias.put( "Encartaciones", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Encartaciones" ).add( new FarmaciaGuardia( "Encartaciones", "22:00-09:00", "(Balmaseda)  Correria, N� 8  |  94 6800233" ) );
		if (mapa.mapaFarmacias.get("Galdakao")==null) mapa.mapaFarmacias.put( "Galdakao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Galdakao" ).add( new FarmaciaGuardia( "Galdakao", "09:00-22:00", "(Galdakao)  Ibarluce, N� 2  |  94 4560138" ) );
		if (mapa.mapaFarmacias.get("Galdakao")==null) mapa.mapaFarmacias.put( "Galdakao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Galdakao" ).add( new FarmaciaGuardia( "Galdakao", "09:00-22:00", "(Galdakao)  Avda, J. Bautista Uriarte,  N� 34  |  94 4560390" ) );
		if (mapa.mapaFarmacias.get("Galdakao")==null) mapa.mapaFarmacias.put( "Galdakao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Galdakao" ).add( new FarmaciaGuardia( "Galdakao", "09:00-22:00", "(Galdakao)  Zamakoa, N� 10  |  94 4564904" ) );
		if (mapa.mapaFarmacias.get("Galdakao")==null) mapa.mapaFarmacias.put( "Galdakao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Galdakao" ).add( new FarmaciaGuardia( "Galdakao", "09:00-22:00", "(Galdakao)  Avda. J. Bautista Uriarte, N� 30  |  94 4561662" ) );
		if (mapa.mapaFarmacias.get("Sestao")==null) mapa.mapaFarmacias.put( "Sestao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Sestao" ).add( new FarmaciaGuardia( "Sestao", "09:00-22:00", "(Sestao)  Ramon Y Cajal, N� 3  |  944963688" ) );
		if (mapa.mapaFarmacias.get("Sestao")==null) mapa.mapaFarmacias.put( "Sestao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Sestao" ).add( new FarmaciaGuardia( "Sestao", "09:00-22:00", "(Sestao)  Aizpuru, N� 12  |  94 4953099" ) );
		if (mapa.mapaFarmacias.get("Sestao")==null) mapa.mapaFarmacias.put( "Sestao", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Sestao" ).add( new FarmaciaGuardia( "Sestao", "09:00-22:00", "(Sestao)  Gran Via, N� 3  |  94 4962130" ) );
		if (mapa.mapaFarmacias.get("Gernika")==null) mapa.mapaFarmacias.put( "Gernika", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Gernika" ).add( new FarmaciaGuardia( "Gernika", "09:00-09:00", "(Gernika-Lumo)  Alh�ndiga, N� 2  |  94 6250885" ) );
		if (mapa.mapaFarmacias.get("Bermeo")==null) mapa.mapaFarmacias.put( "Bermeo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Bermeo" ).add( new FarmaciaGuardia( "Bermeo", "09:00-09:00", "(Bermeo)  Nardiz Tar Jon, N� 4  |  946880134" ) );
		if (mapa.mapaFarmacias.get("Trapagaran")==null) mapa.mapaFarmacias.put( "Trapagaran", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Trapagaran" ).add( new FarmaciaGuardia( "Trapagaran", "09:00-22:00", "(Trapagaran)  1� De Mayo, N� 49  |  944920429" ) );
		if (mapa.mapaFarmacias.get("Portugalete")==null) mapa.mapaFarmacias.put( "Portugalete", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Portugalete" ).add( new FarmaciaGuardia( "Portugalete", "09:00-22:00", "(Casta�os)  Victor Ch�varri, N� 14  |  94 4962045" ) );
		if (mapa.mapaFarmacias.get("Portugalete")==null) mapa.mapaFarmacias.put( "Portugalete", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Portugalete" ).add( new FarmaciaGuardia( "Portugalete", "09:00-22:00", "(Casta�os)  Victor Chavarri, N� 29 (Plaza Del Cristo)  |  94 4962101" ) );
		if (mapa.mapaFarmacias.get("Portugalete")==null) mapa.mapaFarmacias.put( "Portugalete", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Portugalete" ).add( new FarmaciaGuardia( "Portugalete", "09:00-22:00", "(Casta�os)  General Casta�os, N� 41  |  94 4624692" ) );
		if (mapa.mapaFarmacias.get("Portugalete")==null) mapa.mapaFarmacias.put( "Portugalete", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Portugalete" ).add( new FarmaciaGuardia( "Portugalete", "09:00-22:00", "(Repelega)  Carlos Vii,  N� 13  |  94 4624207" ) );
		if (mapa.mapaFarmacias.get("Portugalete")==null) mapa.mapaFarmacias.put( "Portugalete", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Portugalete" ).add( new FarmaciaGuardia( "Portugalete", "09:00-22:00", "(Repelega)  Avda De Repelega, N� 10  |  94 4954034" ) );
		if (mapa.mapaFarmacias.get("Portugalete")==null) mapa.mapaFarmacias.put( "Portugalete", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Portugalete" ).add( new FarmaciaGuardia( "Portugalete", "09:00-22:00", "(Repelega)  San Roque, N� 13  |  94 4958643" ) );
		if (mapa.mapaFarmacias.get("Portugalete")==null) mapa.mapaFarmacias.put( "Portugalete", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Portugalete" ).add( new FarmaciaGuardia( "Portugalete", "09:00-22:00", "(Repelega)  Barrengoitia, S/N (Frente Al Ambulatorio De Rep�lega)  |  94 4958177" ) );
		if (mapa.mapaFarmacias.get("Portugalete")==null) mapa.mapaFarmacias.put( "Portugalete", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Portugalete" ).add( new FarmaciaGuardia( "Portugalete", "22:00-09:00", "(Casta�os)  Avenida Abaro, N� 20  |  94 4611173" ) );
		if (mapa.mapaFarmacias.get("Erandio")==null) mapa.mapaFarmacias.put( "Erandio", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Erandio" ).add( new FarmaciaGuardia( "Erandio", "09:00-22:00", "(Erandio-Desierto)  Obieta, N� 15  |  944674711" ) );
		if (mapa.mapaFarmacias.get("Markina")==null) mapa.mapaFarmacias.put( "Markina", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Markina" ).add( new FarmaciaGuardia( "Markina", "09:00-09:00", "(Markina-Xemein)  Altzibar Auzoa, N� 3 - Etxebarria  |  94 6166356" ) );
		if (mapa.mapaFarmacias.get("Amorebieta")==null) mapa.mapaFarmacias.put( "Amorebieta", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Amorebieta" ).add( new FarmaciaGuardia( "Amorebieta", "09:00-09:00", "(Amorebieta-Etxano)  El Alto - Bide Zahar, N� 14  |  94 6308356" ) );
		if (mapa.mapaFarmacias.get("Amorebieta")==null) mapa.mapaFarmacias.put( "Amorebieta", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Amorebieta" ).add( new FarmaciaGuardia( "Amorebieta", "09:00-22:00", "(Amorebieta-Etxano)  San Miguel, N� 15  |  946733669" ) );
		if (mapa.mapaFarmacias.get("Amorebieta")==null) mapa.mapaFarmacias.put( "Amorebieta", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Amorebieta" ).add( new FarmaciaGuardia( "Amorebieta", "09:00-22:00", "(Amorebieta-Etxano)  Luis Urrengoetxea, N� 5  |  94 6730903" ) );
		if (mapa.mapaFarmacias.get("Durango")==null) mapa.mapaFarmacias.put( "Durango", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Durango" ).add( new FarmaciaGuardia( "Durango", "09:00-09:00", "(Durango)  Muruetatorre, N� 8 Bloque B  |  94 6203461" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "09:00-22:00", "(Algorta)  Juan Bautista Zabala, N� 1 Algorta  |  94 4911798" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "09:00-22:00", "(Algorta)  Esquina Sarrikobaso, Illetas, 12  Algorta  |  94 4301352" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "09:00-22:00", "(Algorta)  Telletxe, N� 1-C - (Frente Estaci�n Metro) Algorta  |  94 4910556" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "09:00-22:00", "(Areeta/Las Arenas)  Amaia, N� 29 - (Frente Al Ambulatorio) Las Arenas  |  94 4805328" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "09:00-22:00", "(Areeta/Las Arenas)  Ibaigane, N� 9  Las Arenas  |  94 4638460" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "09:00-22:00", "(Areeta/Las Arenas)  Andres Larrazabal, N� 5 - Las Arenas  |  94 4637971" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "09:00-22:00", "(Bidezabal)  Avenida Del Angel, N� 2 - (Junto Al Ambulatorio) Algorta  |  94 4914704" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "09:00-22:00", "(Bidezabal)  Bidezabal, N� 2 - (Final Sarrikobaso) Algorta  |  94 4319007" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "09:00-22:00", "(Fadura-Andra Mari)  Maidagan, N� 61-A - Santa Maria De Getxo  |  94 4914697" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "09:00-22:00", "(Fadura-Andra Mari)  Ollarretxe, N� 27 Algorta  |  94 4303328" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "09:00-22:00", "(Romo)  Monte Gorbea, N� 1 - (Romo) Las Arenas  |  94 4630865" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "09:00-22:00", "(Romo)  Ibaiondo, N� 9 - Romo - Las Arenas  |  94 4631809" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "09:00-22:00", "(Villamonte)  Kasune, N� 10 - (Esquina Con Salsidu) Algorta  |  94 4302632" ) );
		if (mapa.mapaFarmacias.get("Getxo")==null) mapa.mapaFarmacias.put( "Getxo", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Getxo" ).add( new FarmaciaGuardia( "Getxo", "22:00-09:00", "(Villamonte)  Arene, N� 4 - (Junto A Oficina D.N.I.) Algorta  |  94 4306570" ) );
		if (mapa.mapaFarmacias.get("Ortuella")==null) mapa.mapaFarmacias.put( "Ortuella", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Ortuella" ).add( new FarmaciaGuardia( "Ortuella", "09:00-22:00", "(Ortuella)  Avda. Del Minero, N� 2  |  94 6640327" ) );
		if (mapa.mapaFarmacias.get("Santurtzi")==null) mapa.mapaFarmacias.put( "Santurtzi", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Santurtzi" ).add( new FarmaciaGuardia( "Santurtzi", "09:00-22:00", "(Buena-Vista)  Avda. Antonio Alzaga, N� 8 (Frente General Casta�os, 95 De Portugalete)  |  94 4618181" ) );
		if (mapa.mapaFarmacias.get("Santurtzi")==null) mapa.mapaFarmacias.put( "Santurtzi", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Santurtzi" ).add( new FarmaciaGuardia( "Santurtzi", "09:00-22:00", "(Kabieces)  J.M. De Barandiar�n, N� 26 (Frente Al Ambulatorio)  |  94 4610369" ) );
		if (mapa.mapaFarmacias.get("Santurtzi")==null) mapa.mapaFarmacias.put( "Santurtzi", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Santurtzi" ).add( new FarmaciaGuardia( "Santurtzi", "09:00-22:00", "(Kabieces)  J.M. De Barandiar�n, N� 6 (Junto Al Ayuntamiento)  |  94 4612035" ) );
		if (mapa.mapaFarmacias.get("Basauri")==null) mapa.mapaFarmacias.put( "Basauri", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Basauri" ).add( new FarmaciaGuardia( "Basauri", "09:00-22:00", "(Ariz)  Aguirre Lehendakari, N� 51 (Metro Ariz, Salida Ambulatorio)  |  944493361" ) );
		if (mapa.mapaFarmacias.get("Basauri")==null) mapa.mapaFarmacias.put( "Basauri", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Basauri" ).add( new FarmaciaGuardia( "Basauri", "09:00-22:00", "(Ariz)  Aguirre Lehendakari, N� 36 ( Plaza Del Mercado)  |  944490494" ) );
		if (mapa.mapaFarmacias.get("Basauri")==null) mapa.mapaFarmacias.put( "Basauri", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Basauri" ).add( new FarmaciaGuardia( "Basauri", "09:00-22:00", "(Centro)  Juan Xxiii, N� 1 (Barrio De Venta)  |  94 4490743" ) );
		if (mapa.mapaFarmacias.get("Basauri")==null) mapa.mapaFarmacias.put( "Basauri", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Basauri" ).add( new FarmaciaGuardia( "Basauri", "09:00-22:00", "(Kareaga)  Karmelo Torre, N� 12 (Cerca Ambulatorio El Kalero)  |  944261326" ) );
		if (mapa.mapaFarmacias.get("Basauri")==null) mapa.mapaFarmacias.put( "Basauri", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Basauri" ).add( new FarmaciaGuardia( "Basauri", "09:00-22:00", "(Kareaga)  Kareaga Goikoa, N� 50 (Ayuntamiento)  |  94 4490643" ) );
		if (mapa.mapaFarmacias.get("Basauri")==null) mapa.mapaFarmacias.put( "Basauri", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Basauri" ).add( new FarmaciaGuardia( "Basauri", "09:00-22:00", "(Kareaga)  Kareaga Goikoa, N� 34 (Al Lado De \"Los Burritos\", Subiendo)  |  94 4498533" ) );
		if (mapa.mapaFarmacias.get("Basauri")==null) mapa.mapaFarmacias.put( "Basauri", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Basauri" ).add( new FarmaciaGuardia( "Basauri", "09:00-22:00", "(Kareaga)  Kareaga Goikoa, N� 16 (El Kalero)  |  94 4491699" ) );
		if (mapa.mapaFarmacias.get("Basauri")==null) mapa.mapaFarmacias.put( "Basauri", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Basauri" ).add( new FarmaciaGuardia( "Basauri", "09:00-22:00", "(Pozokoetxe)  Madrid, N� 6 (Pozokoetxe)  |  94 4491339" ) );
		if (mapa.mapaFarmacias.get("Basauri")==null) mapa.mapaFarmacias.put( "Basauri", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Basauri" ).add( new FarmaciaGuardia( "Basauri", "22:00-09:00", "(Ariz)  Francisco Cortabarria, N� 2, Frente A La Torre De Ariz  |  94 4490587" ) );
		if (mapa.mapaFarmacias.get("Arratia")==null) mapa.mapaFarmacias.put( "Arratia", new ArrayList<FarmaciaGuardia>() );
		mapa.mapaFarmacias.get( "Arratia" ).add( new FarmaciaGuardia( "Arratia", "09:00-22:00", "(Lemoa)  Atutxa Salburua, N� 1 - Lemoa  |  94 6313018" ) );		return mapa;
	}

	
	/** Guarda el mapa en fichero
	 * @param ficheroFarmacias	Fichero en el que guardar el mapa
	 * @throws IOException	Lanzada si hay error de E/S y el mapa no puede guardarse correctamente
	 */
	public void saveToFile( String ficheroFarmacias ) throws IOException {
		// TAREA 2
	Iterator i = mapaFarmacias.values().iterator();
	while (i.hasNext()) {
		ArrayList<FarmaciaGuardia> a = (ArrayList<FarmaciaGuardia>) i.next();
		for (FarmaciaGuardia f : a) {
			BD.farmaciaInsert(st, dia, mes, f);
		}
	}
//			BD.farmaciaInsert(st, dia, mes, farmacia);
	}
	
	/** Consultor del mapa de las farmacias de guardia
	 * @return	Mapa de farmacias, con la localidad como clave y la lista de farmacias de guardia de esa localidad como valor
	 */
	public HashMap<String, ArrayList<FarmaciaGuardia>> getMapaFarmacias() {
		return mapaFarmacias;
	}

	/** Consultor de d�a
	 * @return	D�a del mes en el que las farmacias se encuentran de guardia
	 */
	public int getDia() {
		return dia;
	}

	/** Consultor de mes
	 * @return	Mes (de 1 a 12) en el que las farmacias se encuentran de guardia
	 */
	public int getMes() {
		return mes;
	}
	
}
