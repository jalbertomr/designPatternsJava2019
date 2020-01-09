package com.bext.structural.flyweight.refacguru;

import java.util.*;

public class structureFlyweightForestDemo {

    // Client
    public static void main(String[] args) {
        Forest forest = new Forest();
        forest.plantTree(1,1,"arbolito","green", "liso");
        forest.plantTree(2,1,"arbol","green", "arrugado");
        forest.plantTree(2,2,"arbolito","green", "liso");
        for ( int i = 0; i < 1000; i++) {
            forest.plantRandomTree();
        }
        forest.draw();

        System.out.println("forest.getTreeListCount(): " + forest.getTreeListCount());
        System.out.println("TreeFactory.getTreeTypeCount(): " + TreeFactory.getTreeTypeCount());
    }
}

// Flyweight (repeating state, intrinsic state)
class TreeType {
    String nombre;
    String color;
    String textura;

    public TreeType(String nombre, String color, String textura) {
        this.nombre = nombre;
        this.color = color;
        this.textura = textura;
    }

    public void draw(int x, int y) {
        System.out.println("TreeType.draw on (" + x + ", " + y + "), " + this.nombre + this.color + this.textura);
    }

    public void draw(int x, int y, Tree tree) {
        System.out.println("TreeType.draw on (" + x + ", " + y + "), " + this.nombre + this.color + this.textura +" " + tree.treeType );
    }
}

//flyweight factory
class TreeFactory {
    static Map<String, TreeType> treeTypeMap = new HashMap<>();
    static List<String> nombreList = new ArrayList<>(Arrays.asList("arbolito","arbol","arbolote","canelo","trueno","altito","chaparro"));
    List<String> colorList = new ArrayList<>(Arrays.asList("verde","verdeobscuro","verdeclaro","rojo","rojizo","amarillo","cafe"));
    List<String> texturaList = new ArrayList<>( Arrays.asList("liso","rugoso","aspero","ondulado"));

    private static String getNombreRandom() {
        return nombreList.get( new Random().nextInt( nombreList.size()));
    }

    private static String getColorRandom() {
        return nombreList.get( new Random().nextInt( nombreList.size()));
    }

    private static String getTexturaRandom() {
        return nombreList.get( new Random().nextInt( nombreList.size()));
    }

    public static int getTreeTypeCount(){
        return treeTypeMap.size();
    }

    static TreeType getTreeType(String nombre, String color, String textura) {
        TreeType treeType;
        String key = nombre.concat(color).concat(textura);
        if (treeTypeMap.containsKey(key)) {
            treeType = treeTypeMap.get(key);
        } else {
            treeType = new TreeType(nombre, color, textura);
            treeTypeMap.put(key, treeType);
        }
        return treeType;
    }

    static TreeType getRandomTreeType() {
        return getTreeType( getNombreRandom(), getColorRandom(), getTexturaRandom());
    }
}

// extrinsic State
class Tree {
    int x;
    int y;
    TreeType treeType;

    public Tree(int x, int y, TreeType treeType) {
        this.x = x;
        this.y = y;
        this.treeType = treeType;
    }

    public void draw(Tree tree) {
        treeType.draw( this.x, this.y, tree);
    }
}

// context
class Forest {
    List<Tree> treeList = new ArrayList<>();

    public int getTreeListCount() {
        return treeList.size();
    }

    public void plantTree( int x, int y, String nombre, String color, String textura) {
        TreeType treeType = TreeFactory.getTreeType( nombre, color, textura);
        final Tree tree = new Tree(x, y, treeType);
        treeList.add( tree);
    }

    public void plantRandomTree() {
        TreeType treeType = TreeFactory.getRandomTreeType();
        final Tree tree = new Tree( new Random().nextInt(10), new Random().nextInt(10), treeType);
        treeList.add( tree);
    }

    public void draw() {
        for ( Tree tree: treeList ) {
            tree.draw( tree);
        }
    }
}
