package lyc.compiler.symboltable;

import java.util.HashMap;

public final class SymbolTable {
    private static SymbolTable symt;
    private HashMap<String, Symbol_lyc> table;

    private SymbolTable() {
        table = new HashMap<>();
    }

    public static SymbolTable getSymbolTable(){
        if (symt == null){
            symt = new SymbolTable();
        }
        return symt;
    }

    public void insert(String name,String type, String value, boolean isID){
        if(!isID){
            //constantes empiezan con _ para diferenciar de las IDs
            name = "_"+name;
        }
        table.put(name,new Symbol_lyc(value,type));
    }

    public Symbol_lyc get(String name){
        return table.get(name);
    }

    public boolean exists(String name){
        return table.containsKey(name);
    }

    @Override
    public String toString() {
        //literalmente una tabla
        String inicio = "┌"+"─".repeat(50)+"┬"+"─".repeat(10)+"┬"+"─".repeat(50)+"┬"+"─".repeat(10)+"┐"+"\n";
        String format = "│%-50s│%-10s│%-50s│%-10s│";
        String out = inicio + String.format(format,"NAME","TYPE","VALUE","LENGTH") +"\n";
        String separador = "├"+"─".repeat(50)+"┼"+"─".repeat(10)+"┼"+"─".repeat(50)+"┼"+"─".repeat(10)+"┤"+"\n";
        String fin = "└"+"─".repeat(50)+"┴"+"─".repeat(10)+"┴"+"─".repeat(50)+"┴"+"─".repeat(10)+"┘"+"\n";
        out += separador;
        format = "│%-50s│%s│";
        for(String k : table.keySet() ){
            out += String.format(format,k,table.get(k)) + "\n" + separador;
        }
        return out.substring(0,out.length()-separador.length())+fin;
    }
}
