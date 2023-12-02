package com.bcn.model.Responses;

public class ApiErrorResponse extends ApiResponse{
    
    private Object detalles;



    public ApiErrorResponse(int codigo, String mensaje, Object detalles) {
        super(codigo, mensaje);
        this.detalles = detalles;
    }

    /**
     * @return Object return the detalles
     */
    public Object getDetalles() {
        return detalles;
    }

    /**
     * @param detalles the detalles to set
     */
    public void setDetalles(Object detalles) {
        this.detalles = detalles;
    }

}