package com.mx.forty.util;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.mx.forty.dto.vo.MarcaVo;
import com.mx.forty.controller.ConfiguracionVentaController;

@FacesConverter(forClass = MarcaVo.class, value = "marcaConverter")
public class MarcaConverter implements Converter<MarcaVo> {

    @Override
    public MarcaVo getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.isEmpty()) {
            return null;
        }
        ConfiguracionVentaController bean = context.getApplication()
            .evaluateExpressionGet(context, "#{configuracionesController}", ConfiguracionVentaController.class);

        return bean.getListaMarcas().stream()
                   .filter(m -> String.valueOf(m.getIdMarca()).equals(value))
                   .findFirst()
                   .orElse(null);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, MarcaVo value) {
        if (value == null || value.getIdMarca() == null) {
            return "";
        }
        return String.valueOf(value.getIdMarca());
    }
}
