package com.bcn.model.Responses;

public class ApiSuccessResponse extends ApiResponse{
        private Object response;

        

    public ApiSuccessResponse(int codigo, String mensaje, Object response) {
            super(codigo, mensaje);
            this.response = response;
        }

    /**
     * @return Object return the response
     */
    public Object getResponse() {
        return response;
    }

    /**
     * @param response the response to set
     */
    public void setResponse(Object response) {
        this.response = response;
    }

}