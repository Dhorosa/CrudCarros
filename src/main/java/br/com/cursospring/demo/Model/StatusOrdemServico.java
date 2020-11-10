package br.com.cursospring.demo.Model;

public enum StatusOrdemServico {
    ABERTA, FINALIZADA, CANCELADA;

    StatusOrdemServico() {
    }

    @Override
    public String toString() {
        return "StatusOrdemServico{}";
    }
}
