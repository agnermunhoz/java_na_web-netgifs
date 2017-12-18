# NETGIFS - Atividade Prática
## Disciplina: Java na Web
## Professor: Eduardo Galego
--------------------------------------------

### Setup Inicial
Antes de executar a aplicação, modifique o arquivo ``src/META-INF/persistence.xml`` para as configuraçãos do MySql local.

### Criação de Dados de Exemplo
Para executar a rotina de dados de exemplo, execute como **Java Application** a classe ``br.com.fiap.netgifs.init.InitDatabase``.
Serão criados os usuário ``admin`` senha ``admin`` e o usuário ``user`` senha ``user``.

### Sobre o Projeto
A aplicação foi desenvolvida utilizando JSF na camada de apresentação, JPA para persistência e Servlet para a exibição das imagens salvas em banco.
A classe do Servlet de exibição da imagem é ``br.com.fiap.netgifs.servlet.ViewImage``, para acessar o serviço utilize a URL ``http://servidor:porta/contexto/private/viewimage?imageid=XX`` onde *XX* é o id da imagem no banco.
