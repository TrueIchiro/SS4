package Beispiel_01.Blocks;

//Only Observable
public abstract class EmitBlock implements Block {

    public abstract void create_stream() throws Exception;

    public abstract void create_stream(String filename) throws Exception;

}
