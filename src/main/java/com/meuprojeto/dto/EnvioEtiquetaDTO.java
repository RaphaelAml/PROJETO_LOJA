package com.meuprojeto.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class EnvioEtiquetaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String service;
    private String agency;

    private ToEnvioEtiquetaDTO toEnvioEtiquetaDTO;

    private FromEnvioEtiquetaDTO fromEnvioEtiquetaDTO;

    List<ProductsEnvioEtiquetaDTO> producsts = new ArrayList<ProductsEnvioEtiquetaDTO>();

    List<VolumesEnvioEtiquetaDTO> volumes = new ArrayList<VolumesEnvioEtiquetaDTO>();


    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public ToEnvioEtiquetaDTO getToEnvioEtiquetaDTO() {
        return toEnvioEtiquetaDTO;
    }

    public void setToEnvioEtiquetaDTO(ToEnvioEtiquetaDTO toEnvioEtiquetaDTO) {
        this.toEnvioEtiquetaDTO = toEnvioEtiquetaDTO;
    }

    public FromEnvioEtiquetaDTO getFromEnvioEtiquetaDTO() {
        return fromEnvioEtiquetaDTO;
    }

    public void setFromEnvioEtiquetaDTO(FromEnvioEtiquetaDTO fromEnvioEtiquetaDTO) {
        this.fromEnvioEtiquetaDTO = fromEnvioEtiquetaDTO;
    }

    public List<ProductsEnvioEtiquetaDTO> getProducsts() {
        return producsts;
    }

    public void setProducsts(List<ProductsEnvioEtiquetaDTO> producsts) {
        this.producsts = producsts;
    }

    public List<VolumesEnvioEtiquetaDTO> getVolumes() {
        return volumes;
    }

    public void setVolumes(List<VolumesEnvioEtiquetaDTO> volumes) {
        this.volumes = volumes;
    }
}
