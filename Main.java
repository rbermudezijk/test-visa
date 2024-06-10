import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Main {

    static List<List<Integer>> capturarParticion(List<List<Integer>> ans) {
        List<List<Integer>> container = new ArrayList<>();
        for (List<Integer> subset : ans) {
            container.add(new ArrayList<>(subset));
        }
        return container;
    }

    public static void generadorDeParticion(List<Integer> conjunto, int noDeElementos, List<List<Integer>> particion, List<List<List<Integer>>> particiones) {
        if (noDeElementos == conjunto.size()) {
            particiones.add(capturarParticion(particion));
            return;
        }
 
        for (int i = 0; i < particion.size(); i++) {
            particion.get(i).add(conjunto.get(noDeElementos));
            generadorDeParticion(conjunto, noDeElementos + 1, particion, particiones);
            particion.get(i).remove(particion.get(i).size() - 1);
        }
 
        List<Integer> newSubconjunto = new ArrayList<>();
        newSubconjunto.add(conjunto.get(noDeElementos));
        particion.add(newSubconjunto);
        generadorDeParticion(conjunto, noDeElementos + 1, particion, particiones);
        particion.remove(particion.size() - 1);
    }
 
    public static List<List<List<Integer>>> generadorDeParticiones(List<Integer> conjunto) {
        List<List<Integer>> particion = new ArrayList<>();
        List<List<List<Integer>>> particiones = new ArrayList<>();
        generadorDeParticion(conjunto, 0, particion, particiones);
        return particiones;
    }

    public static Boolean evaluarParticion(List<List<Integer>> particion) {
        if (particion.size() < 3) {
            return false;
        }
        int valorNormalizado = particion.get(0).stream().reduce(0, Integer::sum);

        for (List<Integer> subconjunto: particion){
            if (subconjunto.stream().reduce(0, Integer::sum)!=valorNormalizado) {
                return false;
            }
        }
        return true;
    }

    public static Boolean evaluarParticiones(List<List<List<Integer>>> particiones) {
        for(List<List<Integer>> particion: particiones) {
            if (evaluarParticion(particion)) {
                System.out.println("Posible solución: " + particion);
                return true;
            }
        }
        return false;
    }

    public static Boolean solucion(Integer[] arreglo){
        List<Integer> conjunto = new ArrayList<>(Arrays.asList(arreglo));
        System.out.println("Conjunto de entrada: " + conjunto);
        List<List<List<Integer>>> particiones = generadorDeParticiones(conjunto);
        return evaluarParticiones(particiones);
    }

    public static void main(String[] args) {
        Integer[] arreglo = new Integer[] {2, 5, 9, 4, 2, 3, 6, 1, 9, 7};
        System.out.println(solucion(arreglo));
    }
}