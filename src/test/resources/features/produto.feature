Feature: Gerenciamento de produto via HTTP

  @tagPost
  Scenario Outline: Criar um novo produto
    Given que o cliente envia uma solicitação para criar um novo produto com nome <nome>, descricao <descricao> e preco <preco>
    When cliente recebe a solicitação com código de status <status>
    Then o cliente recebe a resposta com os mesmos dados do produto criado, com nome <nome>, descricao <descricao> e preco <preco>

    Examples:
      | nome   |  descricao                  | preco | status |
      | Teste1 | Descricao do Produto Teste1 | 50    | 201    |
      |        | Descricao do Produto Teste2 | 30    | 400    |
      | Teste1 |                             | 30    | 400    |

  @tagGet
  Scenario Outline: Recuperar informações de um produto pelo ID
    Given que o cliente envia uma solicitação para recuperar informações de um produto pelo ID <id>
    When cliente recebe a solicitação com código de status <status>
    Then recebe as informações corretas do produto na resposta

    Examples:
      | id    | status |
      | 100   | 200    |
      | 0     | 404    |

  @tagPut
  Scenario Outline: Editar informações de um produto pelo ID
    Given que o cliente envia uma solicitação para editar informações de um produto pelo ID <id> com os dados novos
    When cliente recebe a solicitação com código de status <status>
    Then o cliente recebe a resposta de ID <id> com os novos dados do produto

    Examples:
      | id    | status |
      | 101   | 200    |

  @tagDelete
  Scenario Outline: Deletar um produto pelo ID
    Given que o cliente envia uma solicitação para deletar um produto pelo ID <id>
    When cliente recebe apenas o código de status <status>
    Then o produto é removido com sucesso e o cliente recebe uma resposta vazia

    Examples:
      | id    | status |
      | 102   | 204    |
      | 0     | 404    |

  @tagGet
  Scenario Outline: Recuperar produtos por categoria
    Given que o cliente envia uma solicitação para recuperar produtos por categoria <categoria>
    When cliente recebe as informacoes com o codigo <status>
    Then recebe uma lista de produtos da categoria na resposta

    Examples:
      | categoria       | status |
      | LANCHE          | 200    |

  @tagPatch
  Scenario Outline: Alterar imagem de um produto pelo ID
    Given que o cliente envia uma solicitação para alterar a imagem de um produto pelo ID <id> com a nova imagem <imagem>
    When cliente recebe a solicitação com código de status <status>
    Then o cliente recebe a resposta com a imagem do produto atualizada

    Examples:
      | id    | imagem                              | status |
      | 103   | https://nova-imagem.com/produto.jpg | 200    |
      | 0     | https://nova-imagem.com/produto.jpg | 404    |