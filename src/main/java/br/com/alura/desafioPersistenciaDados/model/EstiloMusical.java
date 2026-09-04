package br.com.alura.desafioPersistenciaDados.model;

public enum EstiloMusical {
    PAGODE("Pagode"),
    FORRO("Forró"),
    ARROCHA("Arrocha"),
    MPB("MPB"),
    ELETRONICA("Eletrônica"),
    BREGA("Brega"),
    REGGAE("Reggae"),
    RAP("RAP"),
    GOSPEL("Gospel"),
    POP("POP"),
    ROCK("Rock"),
    SERTANEJO("Sertanejo");

    private String estiloMusical;

    EstiloMusical(String estiloMusical) {
        this.estiloMusical = estiloMusical;
    }

    public static EstiloMusical selecaoEstiloMusical(String estiloMusical) {
        for (EstiloMusical estilo : EstiloMusical.values()) {
            if (estilo.estiloMusical.toLowerCase().contains(estiloMusical.toLowerCase())) {
                return estilo;
            }
        }
        throw new IllegalArgumentException("Estilo musical não encontrado!");
    }
}
