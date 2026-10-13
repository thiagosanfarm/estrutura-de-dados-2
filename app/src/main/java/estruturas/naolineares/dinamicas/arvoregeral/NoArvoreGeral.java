package estruturas.naolineares.dinamicas.arvoregeral;

public class NoArvoreGeral<T> extends NoAbstratoArvoreGeral<T, NoArvoreGeral<T>> {
    public NoArvoreGeral(T elemento) {
        super(elemento);
    }
}