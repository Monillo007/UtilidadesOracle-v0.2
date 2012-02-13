/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package superclases;

/**
 *
 * @author lnavarrora
 */
public class DatoTabla {

    String nombre;
    String tipo;
    String isNull;
    int tamanio;

    int precision;

    public DatoTabla() {
    }

    public DatoTabla(String nombre, String tipo, String isNull, int tamanio) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.isNull = isNull;
        this.tamanio = tamanio;
    }

    public String getIsNull() {
        return isNull;
    }

    public void setIsNull(String isNull) {
        this.isNull = isNull;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void setTamanio(int tamanio) {
        this.tamanio = tamanio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public String toString(){
        return "["+nombre+","+tipo+","+tamanio+","+precision+","+isNull+"]";
    }
}
