package school.sptech.estoque01222143matheuslessa.Controller;

import org.springframework.web.bind.annotation.*;
import school.sptech.estoque01222143matheuslessa.Entity.Produto;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/estoque")
public class EstoqueController {
    private List<Produto> listaProduto = new ArrayList<>();

    @GetMapping
    public List<Produto> listar(){
        return listaProduto;
    }

    @PostMapping("/produtos")
    public Produto cadastrar(@RequestBody Produto estoque){
        listaProduto.add(estoque);
        return estoque;
    }

    @PutMapping("/{indice}")
    public List<Produto> atualizar(@PathVariable int indice, @RequestBody Produto estoqueAtualizado){
        if(indice >= 0 && indice < listaProduto.size()){
            listaProduto.set(indice,estoqueAtualizado);
            return listaProduto;
        }else {
            return null;
        }
    }

    @GetMapping("/{qtdEstoque}")
    public List<Produto> buscarProdutoEstoqueMaior(@PathVariable int qtdEstoque){
        List<Produto> produtosComEstoqueMaior = new ArrayList<>();

        for (Produto produto : listaProduto) {
            if (produto.getQtdEstoque() > qtdEstoque) {
                produtosComEstoqueMaior.add(produto);
            }
        }
        return produtosComEstoqueMaior;
    }
}
