package Utilerias;

public abstract class TablaBD {
    protected String[] campos; // Arreglo de tipo cadena para guardar los nombres de los campos
    protected String[] tipos; // Arreglo de tipo cadena para guardar los tipos de los campos
    protected int[] tamanos; // Arreglo de numeros enteros para guardar los tamaños de cada campo
    protected boolean[] obligatorios; // Arreglo de booleanos para indicar si el campo es obligatorio o no
    
    /*
     * Constructor por defecto
     */
    public  TablaBD(){}

    /*
     * Metodo utilizado para establecer un valor a un campo especifico
     * @param p_nombrecampo El nombre del campo a establecer
     * @param p_valorcampo El valor del campo
     */
    public abstract void setCampo(String p_nombrecampo, Object p_valorcampo);
    
    /*
     * Genera la cadena para insertar registros en la bd
     */
    public abstract String getSQLInsertar();
    
    /*
     * Genera la cadena para modificar registros en la bd
     */
    public abstract String getSQLModificar();

    /*
     * Obtiene el campo que es id de la tabla
     */
    public abstract String getID();
    
    public String[] getCampos(){
        return campos;
    }
    
    public String[] getTipos(){
        return tipos;
    }
    
    public int[] getTamanos(){
        return tamanos;
    }
    
    public int getTamano(String p_campo){
        int posicion = 0;
	while (posicion < campos.length && !campos[posicion].equals(p_campo))
  	{
            posicion ++;
	}
        if (posicion >= campos.length)
            posicion = 0;
        return tamanos[posicion];
    }
    
    public void setTamano(int posicion, int tamano){
        if (tamanos == null){
            int numcampos = campos.length;
            tamanos = new int[numcampos];
        }
        tamanos[posicion] = tamano;
    }

    public void setObligatorios(int posicion, boolean obligatorio){
        if(obligatorios == null){
            int numcampos = campos.length;
            obligatorios = new boolean[numcampos];
        }
        obligatorios[posicion] = obligatorio;
    }

    public boolean isObligatorio(String campo){
        int posicion = 0;
        while (posicion < campos.length && !campos[posicion].equals(campo)){
          posicion ++;
        }
        if (posicion >= campos.length)posicion = 0;

        return obligatorios[posicion];
    }

    public void setTipos(int posicion, String tipo){
        if(tipos == null){
            tipos = new String[campos.length];
        }
        tipos[posicion] = tipo;
    }

    public String getTipo(String campo){
        int posicion = 0;
        while (posicion < campos.length && !campos[posicion].equals(campo))
        {
                posicion ++;
        }
        if (posicion >= campos.length)
            posicion = 0;
        String tipo = "";

        if(tipos[posicion].equals("VARCHAR") ||tipos[posicion].equals("VARCHAR2") || tipos[posicion].equals("CHAR") || tipos[posicion].equals("NVARCHAR2")){
            tipo = "String";
        }else if(tipos[posicion].equals("NUMBER") || tipos[posicion].equals("INTEGER")){
            tipo = "BigDecimal";
        }else if(tipos[posicion].equals("DATE")){
            tipo = "Date";
        }else if(tipos[posicion].equals("LONG RAW") || tipos[posicion].equals("BLOB")){
            tipo = "File";
        }

        return tipo;
    }
    
}