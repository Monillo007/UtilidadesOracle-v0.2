/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utilerias;

import java.util.ArrayList;
import superclases.Combo;

/**
 *
 * @author zeus
 */
public class VariablesGlobales {
    private static String esquema;
    private static String servidor;
    private static String puerto;
    private static String BD;
    private static String usuario;
    private static String contra;
    private static String rutaDestino;
    private static String paqClaseTabla;
    private static String paqClases;
    private static String nombreArchivo;
    private static String nombreFormulario;
    private static String tituloTabla;
    private static String tipoConsecutivo;
    private static String idTabla;
    private static int numConsecutivo;
    private static int totalCamposVisibles;
    private static ArrayList<String> camposOcultos;
    private static ArrayList<Combo> combos;
    private static ArrayList<String> requeridos;
    private static ArrayList<String> selUsuario;
    private static ArrayList<String> selPersona;
    private static ArrayList<String> catalogos;
    private static ArrayList<String> estados;
    private static ArrayList<String> municipios;
    private static ArrayList<String> colonias;
    private static ArrayList<String> calles;

    public static ArrayList<Combo> getCombos() {
        return combos;
    }

    public static void setCombos(ArrayList<Combo> combos) {
        VariablesGlobales.combos = combos;
    }
    private static String rutaJavascript;

    public static String getRutaJavascript() {
        return rutaJavascript;
    }

    public static void setRutaJavascript(String rutaJavascript) {
        VariablesGlobales.rutaJavascript = rutaJavascript;
    }

    public static ArrayList<String> getCamposOcultos() {
        return camposOcultos;
    }

    public static void setCamposOcultos(ArrayList<String> camposOcultos) {
        VariablesGlobales.camposOcultos = camposOcultos;
    }

    public static String getImportString() {
        return importString;
    }

    public static void setImportString(String importString) {
        VariablesGlobales.importString = importString;
    }

    public static String getNombreArchivo() {
        return nombreArchivo;
    }

    public static void setNombreArchivo(String nombreArchivo) {
        VariablesGlobales.nombreArchivo = nombreArchivo;
    }

    public static String getNombreFormulario() {
        return nombreFormulario;
    }

    public static void setNombreFormulario(String nombreFormulario) {
        VariablesGlobales.nombreFormulario = nombreFormulario;
    }

    public static String getTituloTabla() {
        return tituloTabla;
    }

    public static void setTituloTabla(String tituloTabla) {
        VariablesGlobales.tituloTabla = tituloTabla;
    }
    private static String importString;
    private static ArrayList<String> tablas;
    private static ArrayList<String> campos;

    public static ArrayList<String> getCampos() {
        return campos;
    }

    public static void setCampos(ArrayList<String> campos) {
        VariablesGlobales.campos = campos;
    }

    public static String getEsquema() {
        return esquema;
    }

    public static void setEsquema(String esquema) {
        VariablesGlobales.esquema = esquema;
    }

    /**
     * @return the servidor
     */
    public static String getServidor() {
        return servidor;
    }

    /**
     * @param aServidor the servidor to set
     */
    public static void setServidor(String aServidor) {
        servidor = aServidor;
    }

    /**
     * @return the puerto
     */
    public static String getPuerto() {
        return puerto;
    }

    /**
     * @param aPuerto the puerto to set
     */
    public static void setPuerto(String aPuerto) {
        puerto = aPuerto;
    }

    /**
     * @return the BD
     */
    public static String getBD() {
        return BD;
    }

    /**
     * @param aBD the BD to set
     */
    public static void setBD(String aBD) {
        BD = aBD;
    }

    /**
     * @return the usuario
     */
    public static String getUsuario() {
        return usuario;
    }

    /**
     * @param aUsuario the usuario to set
     */
    public static void setUsuario(String aUsuario) {
        usuario = aUsuario;
    }

    /**
     * @return the contra
     */
    public static String getContra() {
        return contra;
    }

    /**
     * @param aContra the contra to set
     */
    public static void setContra(String aContra) {
        contra = aContra;
    }

    /**
     * @return the tablas
     */
    public static ArrayList<String> getTablas() {
        return tablas;
    }

    /**
     * @param aTablas the tablas to set
     */
    public static void setTablas(ArrayList<String> aTablas) {
        tablas = aTablas;
    }

    /**
     * @return the rutaDestino
     */
    public static String getRutaDestino() {
        return rutaDestino;
    }

    /**
     * @param aRutaDestino the rutaDestino to set
     */
    public static void setRutaDestino(String aRutaDestino) {
        rutaDestino = aRutaDestino;
    }

    /**
     * @return the paqClaseTabla
     */
    public static String getPaqClaseTabla() {
        return paqClaseTabla;
    }

    /**
     * @param aPaqClaseTabla the paqClaseTabla to set
     */
    public static void setPaqClaseTabla(String aPaqClaseTabla) {
        paqClaseTabla = aPaqClaseTabla;
    }

    /**
     * @return the paqClases
     */
    public static String getPaqClases() {
        return paqClases;
    }

    /**
     * @param aPaqClases the paqClases to set
     */
    public static void setPaqClases(String aPaqClases) {
        paqClases = aPaqClases;
    }

    /**
     * @return the tipoConsecutivo
     */
    public static String getTipoConsecutivo() {
        return tipoConsecutivo;
    }

    /**
     * @param aTipoConsecutivo the tipoConsecutivo to set
     */
    public static void setTipoConsecutivo(String aTipoConsecutivo) {
        tipoConsecutivo = aTipoConsecutivo;
    }

    /**
     * @return the numConsecutivo
     */
    public static int getNumConsecutivo() {
        return numConsecutivo;
    }

    /**
     * @param aNumConsecutivo the numConsecutivo to set
     */
    public static void setNumConsecutivo(int aNumConsecutivo) {
        numConsecutivo = aNumConsecutivo;
    }

    /**
     * @return the requeridos
     */
    public static ArrayList<String> getRequeridos() {
        return requeridos;
    }

    /**
     * @param aRequeridos the requeridos to set
     */
    public static void setRequeridos(ArrayList<String> aRequeridos) {
        requeridos = aRequeridos;
    }

    /**
     * @return the selUsuario
     */
    public static ArrayList<String> getSelUsuario() {
        return selUsuario;
    }

    /**
     * @param aSelUsuario the selUsuario to set
     */
    public static void setSelUsuario(ArrayList<String> aSelUsuario) {
        selUsuario = aSelUsuario;
    }

    /**
     * @return the catalogos
     */
    public static ArrayList<String> getCatalogos() {
        return catalogos;
    }

    /**
     * @param aCatalogos the catalogos to set
     */
    public static void setCatalogos(ArrayList<String> aCatalogos) {
        catalogos = aCatalogos;
    }

    /**
     * @return the estados
     */
    public static ArrayList<String> getEstados() {
        return estados;
    }

    /**
     * @param aEstados the estados to set
     */
    public static void setEstados(ArrayList<String> aEstados) {        
        estados = aEstados;
    }

    /**
     * @return the municipios
     */
    public static ArrayList<String> getMunicipios() {
        return municipios;
    }

    /**
     * @param aMunicipios the municipios to set
     */
    public static void setMunicipios(ArrayList<String> aMunicipios) {
        municipios = aMunicipios;
    }

    /**
     * @return the colonias
     */
    public static ArrayList<String> getColonias() {
        return colonias;
    }

    /**
     * @param aColonias the colonias to set
     */
    public static void setColonias(ArrayList<String> aColonias) {
        colonias = aColonias;
    }

    /**
     * @return the calles
     */
    public static ArrayList<String> getCalles() {
        return calles;
    }

    /**
     * @param aCalles the calles to set
     */
    public static void setCalles(ArrayList<String> aCalles) {
        calles = aCalles;
    }

    /**
     * @return the totalCamposVisibles
     */
    public static int getTotalCamposVisibles() {
        return totalCamposVisibles;
    }

    /**
     * @param aTotalCamposVisibles the totalCamposVisibles to set
     */
    public static void setTotalCamposVisibles(int aTotalCamposVisibles) {
        totalCamposVisibles = aTotalCamposVisibles;
    }

    /**
     * @return the idTabla
     */
    public static String getIdTabla() {
        return idTabla;
    }

    /**
     * @param aIdTabla the idTabla to set
     */
    public static void setIdTabla(String aIdTabla) {
        idTabla = aIdTabla;
    }

    public static void setSelPersona(ArrayList<String> selPersona) {
        VariablesGlobales.selPersona = selPersona;
    }
    
    public static ArrayList<String> getSelPersona(){
        return selPersona;
    }

}
