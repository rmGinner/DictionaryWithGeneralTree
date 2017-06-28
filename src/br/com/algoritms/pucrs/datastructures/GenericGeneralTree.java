package br.com.algoritms.pucrs.datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import br.com.algoritms.pucrs.exceptions.EmptyTreeException;

public class GenericGeneralTree<T> {

    // Classe interna Node
    private class Node {

        public Node father;
        public T element;
        public ArrayList<Node> subtrees;
        public Integer identity;
                
        public Node(T element,Integer identity) {
            father = null;
            this.element = element;
            this.identity = identity;
            subtrees = new ArrayList<>();
        }
        
        public Node(T element, Node father,Integer identity) {
            this.father = father;
            this.element = element;
            this.identity = identity;
            subtrees = new ArrayList<>();
        }

        public void addSubtree(Node n,Integer identity) {
        	this.identity = identity;
            n.father = this;
            subtrees.add(n);
        }

        public boolean removeSubtree(Node n) {
            n.father = null;
            return subtrees.remove(n);
        }

        public Node getSubtree(int i) {
            if ((i < 0) || (i >= subtrees.size())) {
                throw new IndexOutOfBoundsException();
            }
            return subtrees.get(i);
        }

        public int getSubtreesSize() {
            return subtrees.size();
        }
    }

    // Atributos
    private Node root;
    private int count;

    // Metodos
    public GenericGeneralTree() {
        root = null;
        count = 0;
    }

    public T getRoot() {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        return root.element;
    }

    public void setRoot(T element,Integer identity) {
        if (isEmpty()) {
            throw new EmptyTreeException();
        }
        root.element = element;
        root.identity = identity;
    }

    public boolean isRoot(Integer element) {
        if (root != null) {
            if (root.element.equals(element)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public int size() {
        return count;
    }

    public void clear() {
        root = null;
        count = 0;
    }

    public T getFaher(T element,Integer identity) {
        Node n = searchNodeRef(element,identity, root);
        if (n == null || n.father == null) {
            return null;
        } else {
            return n.father.element;
        }
    }
    
    public boolean contains(T element,Integer identity) {
        Node nAux = searchNodeRef(element,identity, root);
        return (nAux != null);
    }

    private Node searchNodeRef(T element,Integer identity, Node target) {
        Node res = null;
        if (target != null) {
            if (element.equals(target.element) && identity.equals(target.identity)) {
                res = target;
            } else {
                Node aux = null;
                int i = 0;
                while ((aux == null) && (i < target.getSubtreesSize())) {
                    aux = searchNodeRef(element,identity, target.getSubtree(i));
                    i++;
                }
                res = aux;
            }
        }
        return res;
    }

    public boolean add(T element,Integer identity, T father) {
        Node n = new Node(element,identity);
        Node nAux = null;
        boolean res = false;
        if (father == null) {   // Insere na raiz 	
            if (root != null) { //Atualiza o pai da raiz
                n.addSubtree(root,identity);
                root.father = n;
            }
            root = n;   //Atualiza a raiz
            res = true;
        } else {        //Insere no meio da Ã�rvore
            nAux = searchNodeRef(father,identity, root);
            if (nAux != null) {
                nAux.addSubtree(n,identity);
                res = true;
            }
        }
        count++;
        return res;
    }


    public ArrayList<T> positionsPre() {
        ArrayList<T> lista = new ArrayList<>();
        positionsPreAux(root, lista);
        return lista;
    }

    private void positionsPreAux(Node n, ArrayList<T> lista) {
        if (n != null) {
            lista.add(n.element);
            for (int i = 0; i < n.getSubtreesSize(); i++) {
                positionsPreAux(n.getSubtree(i), lista);
            }
        }
    }
    
    public ArrayList<T> positionsWidth() {
        ArrayList<T> lista = new ArrayList<>();

        Queue<Node> fila = new LinkedList<>();
        Node atual;

        if (root != null) {
            fila.offer(root);
            while (!fila.isEmpty()) {
                atual = fila.poll();
                lista.add(atual.element);
                for (int i = 0; i < atual.getSubtreesSize(); i++) {
                    fila.offer(atual.getSubtree(i));
                }
            }
        }
        return lista;
    }

}
