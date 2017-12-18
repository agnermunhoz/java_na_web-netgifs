# NETGIFS - Atividade Pr�tica
## Disciplina: Java na Web
## Professor: Eduardo Galego
--------------------------------------------

### Setup Inicial
Antes de executar a aplica��o, modifique o arquivo ``src/META-INF/persistence.xml`` para as configura��os do MySql local.

### Cria��o de Dados de Exemplo
Para executar a rotina de dados de exemplo, execute como **Java Application** a classe ``br.com.fiap.netgifs.init.InitDatabase``.
Ser�o criados os usu�rio ``admin`` senha ``admin`` e o usu�rio ``user`` senha ``user``.

### Sobre o Projeto
A aplica��o foi desenvolvida usulizando JSF na camada de apresenta��o, JPA para persist�ncia e Servlet para a exibi��o das imagens salvas em banco.
A classe do Servlet de exibi��o da imagem � ``br.com.fiap.netgifs.servlet.ViewImage``, para acessar o servi�o utilize a URL ``http://servidor:porta/contexto/private/viewimage?imageid=XX`` onde *XX* � o id da imagem no banco.