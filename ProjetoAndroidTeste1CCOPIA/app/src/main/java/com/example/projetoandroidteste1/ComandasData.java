package com.example.projetoandroidteste1;

import android.content.Context;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class ComandasData {
    private static Map<Integer, Comanda> comandas = new HashMap<>();
    private static final String FILE_NAME = "comandas_data.dat";

    // Inicializa a classe com o contexto da aplicação
    public static void initialize(Context context) {
        carregarDados(context);
    }

    public static Comanda getComanda(int numero) {
        if (!comandas.containsKey(numero)) {
            comandas.put(numero, new Comanda(numero));
        }
        return comandas.get(numero);
    }

    public static void salvarComanda(Comanda comanda) {
        comandas.put(comanda.getNumero(), comanda);
        salvarDados(MainActivity.getContext());
    }

    public static Collection<Comanda> getAllComandas() {
        return comandas.values();
    }

    public static boolean existeComanda(int numero) {
        return comandas.containsKey(numero);
    }

    private static void salvarDados(Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        try (FileOutputStream fos = new FileOutputStream(file);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {

            oos.writeObject(comandas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void carregarDados(Context context) {
        File file = new File(context.getFilesDir(), FILE_NAME);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file);
                 ObjectInputStream ois = new ObjectInputStream(fis)) {

                comandas = (Map<Integer, Comanda>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static void excluirComanda(int numero) {
        comandas.remove(numero);
        salvarDados(MainActivity.getContext());
    }
}
