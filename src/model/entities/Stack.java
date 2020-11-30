package model.entities;

import model.exceptions.StackException;

public class Stack<T> {

    private class Node {
        T item;
        Node next;
    }

    private Node top;
    private int size;

    public Stack() {
        top = null;
        size = 0;
    }

    /** Método recebe um objeto do tipo T por parâmetro e o insere na pilha.
     * @param t Objeto que será adicionado na pilha
     */
    public void push(T t) {
        if (t == null)
            throw new NullPointerException("The parameter can't be null.");
        else {
            Node node = new Node();
            node.item = t;
            node.next = top;
            top = node;
            size++;
        }
    }

    /** Método que remove da pilha o último elemento que foi adicionado na mesma.
     * @return T Item que foi excluído da pilha
     * @throws StackException Exceção caso a pilha esteja vazia
     */
    public T pop() throws StackException {
        if (isEmpty())
            throw new StackException("The Stack is empty!");
        else {
            T item = top.item;
            top = top.next;
            size--;
            return item;
        }
    }

    /** Método responsável por retornar uma nova pilha invertida sem alterar a pilha atual.
     * @return Stack<T> Pilha invertida
     * @throws StackException
     */
    public Stack<T> reverseStack() throws StackException {
        Stack<T> reverse = new Stack();
        if (this.isEmpty())
            throw new StackException("The Stack is empty!");
        Node aux = this.top;
        while (aux != null) {
            reverse.push(aux.item);
            aux = aux.next;
        }
        return reverse;
    }

    /** Método utilizado para retornar o elemento que está no topo da pilha sem o remover da mesma.
     * Uma exceção do tipo StackException é lançada caso a pilha esteja vazia.
     * @return T - Elemento no topo da lista.
     */
    public T peek() {
        if (isEmpty())
            throw new StackException("The Stack is empty!");

        return this.top.item;
    }

    /** Método verifica se a pilha está vazia. Caso esteja retorna true, caso não, retorna false.
     * @return boolean - Indica se a pilha está vazia ou não.
     */
    public boolean isEmpty() {
        return (this.top == null);
    }

    /** Método retorna o tamanho da pilha.
     * @return int - Tamanho da pilha
     */
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node aux = top;
        while (aux != null) {
            sb.append(aux.item);
            aux = aux.next;
        }
        return sb.toString();
    }

}