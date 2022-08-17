package com.algaworks.ecommerce.jpql;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.junit.Test;

import com.algaworks.ecommerce.EntityManagerTest;
import com.algaworks.ecommerce.model.Cliente;
import com.algaworks.ecommerce.model.Pedido;

public class BasicoJPQLTest extends EntityManagerTest {
	
	@Test 
	public void selecionarAtributoParaRetorno() {
		String jpql = "select p.nome from Produto p";
		
		TypedQuery<String> typedQuery = entityManager.createQuery(jpql, String.class);
		List<String> produtos = typedQuery.getResultList();		

		assertTrue(String.class.equals(produtos.get(0).getClass()));
		
		String jpqlCliente = "select p.cliente from Pedido p";
		TypedQuery<Cliente> typedQueryCliente = entityManager.createQuery(jpqlCliente, Cliente.class);
		List<Cliente> clientes = typedQueryCliente.getResultList();
		
		assertTrue(Cliente.class.equals(clientes.get(0).getClass()));
		
	}

    @Test
    public void buscarPorIdentificador() {
        // entityManager.find(Pedido.class, 1)

        TypedQuery<Pedido> typedQuery = entityManager
                .createQuery("select p from Pedido p where p.id = 1", Pedido.class);

        Pedido pedido = typedQuery.getSingleResult();
        assertNotNull(pedido);

//        List<Pedido> lista = typedQuery.getResultList();
//        Assert.assertFalse(lista.isEmpty());
    }
    
    @Test
    public void mostrardiferencaQueries() {
    	String jpql = "select p from Pedido p where p.id = 1";
    	
    	TypedQuery<Pedido> typedQuery = entityManager.createQuery(jpql, Pedido.class);
    	Pedido pedido1 = typedQuery.getSingleResult();
    	assertNotNull(pedido1);
    	
    	Query query = entityManager.createQuery(jpql);
    	Pedido pedido2 = (Pedido) query.getSingleResult();
    	assertNotNull(pedido2);
    	
//    	List<Pedido> pedidos = query.getResultList();
//    	Assert.assertFalse(pedidos.isEmpty());
    
    }
}
