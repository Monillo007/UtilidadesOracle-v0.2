package utilerias;

import java.math.BigDecimal;
import java.util.Date;


public class example extends Utilerias.TablaBD{
     private int idmenu;
     private int idmenupadre;
     private String descripcion;
     private String etiqueta;
     private String programa;
     private int orden;
     private int esdivisora;
     private String imagen;
     private String estatusregistro;
     private String usuariocrea;
     private Date fechacrea;
     private String usuariomodifica;
     private Date fechamodifica;

    public example() {
    }

    public example(int idmenu, int idmenupadre, String descripcion, String etiqueta, String programa, int orden, int esdivisora, String imagen, String estatusregistro, String usuariocrea, Date fechacrea, String usuariomodifica, Date fechamodifica) {
        this.idmenu = idmenu;
        this.idmenupadre = idmenupadre;
        this.descripcion = descripcion;
        this.etiqueta = etiqueta;
        this.programa = programa;
        this.orden = orden;
        this.esdivisora = esdivisora;
        this.imagen = imagen;
        this.estatusregistro = estatusregistro;
        this.usuariocrea = usuariocrea;
        this.fechacrea = fechacrea;
        this.usuariomodifica = usuariomodifica;
        this.fechamodifica = fechamodifica;
    }
     
    
    /**
     * @return the idmenu
     */
    public int getIdmenu() {
        return idmenu;
    }

    /**
     * @param idmenu the idmenu to set
     */
    public void setIdmenu(int idmenu) {
        this.idmenu = idmenu;
    }

    /**
     * @return the idmenupadre
     */
    public int getIdmenupadre() {
        return idmenupadre;
    }

    /**
     * @param idmenupadre the idmenupadre to set
     */
    public void setIdmenupadre(int idmenupadre) {
        this.idmenupadre = idmenupadre;
    }

    /**
     * @return the descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return the etiqueta
     */
    public String getEtiqueta() {
        return etiqueta;
    }

    /**
     * @param etiqueta the etiqueta to set
     */
    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    /**
     * @return the programa
     */
    public String getPrograma() {
        return programa;
    }

    /**
     * @param programa the programa to set
     */
    public void setPrograma(String programa) {
        this.programa = programa;
    }

    /**
     * @return the orden
     */
    public int getOrden() {
        return orden;
    }

    /**
     * @param orden the orden to set
     */
    public void setOrden(int orden) {
        this.orden = orden;
    }

    /**
     * @return the esdivisora
     */
    public int getEsdivisora() {
        return esdivisora;
    }

    /**
     * @param esdivisora the esdivisora to set
     */
    public void setEsdivisora(int esdivisora) {
        this.esdivisora = esdivisora;
    }

    /**
     * @return the imagen
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * @param imagen the imagen to set
     */
    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    /**
     * @return the estatusregistro
     */
    public String getEstatusregistro() {
        return estatusregistro;
    }

    /**
     * @param estatusregistro the estatusregistro to set
     */
    public void setEstatusregistro(String estatusregistro) {
        this.estatusregistro = estatusregistro;
    }

    /**
     * @return the usuariocrea
     */
    public String getUsuariocrea() {
        return usuariocrea;
    }

    /**
     * @param usuariocrea the usuariocrea to set
     */
    public void setUsuariocrea(String usuariocrea) {
        this.usuariocrea = usuariocrea;
    }

    /**
     * @return the fechacrea
     */
    public Date getFechacrea() {
        return fechacrea;
    }

    /**
     * @param fechacrea the fechacrea to set
     */
    public void setFechacrea(Date fechacrea) {
        this.fechacrea = fechacrea;
    }

    /**
     * @return the usuariomodifica
     */
    public String getUsuariomodifica() {
        return usuariomodifica;
    }

    /**
     * @param usuariomodifica the usuariomodifica to set
     */
    public void setUsuariomodifica(String usuariomodifica) {
        this.usuariomodifica = usuariomodifica;
    }

    /**
     * @return the fechamodifica
     */
    public Date getFechamodifica() {
        return fechamodifica;
    }

    /**
     * @param fechamodifica the fechamodifica to set
     */
    public void setFechamodifica(Date fechamodifica) {
        this.fechamodifica = fechamodifica;
    }

    @Override
    public void setCampo(String p_nombrecampo, Object p_valorcampo) {
        
    }

    @Override
    public String getSQLInsertar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getSQLModificar() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getID() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}