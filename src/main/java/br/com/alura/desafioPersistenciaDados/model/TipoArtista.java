package br.com.alura.desafioPersistenciaDados.model;

public enum TipoArtista {
    SOLO("Solo"),
    DUPLA("Dupla"),
    BANDA("Banda");

    private String tipoArtista;

    TipoArtista(String tipoArtista) {
        this.tipoArtista = tipoArtista;
    }

    public static TipoArtista selecaoTipoArtista(String tipoArtista) {
        for(TipoArtista tipo : TipoArtista.values()){
            if(tipo.tipoArtista.toLowerCase().contains(tipoArtista.toLowerCase())){
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nenhum tipo compatível encontrado!");
    }

}
