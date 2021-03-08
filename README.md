*******
### FILMES TMDB
*******
App Android Nativo escrito em Kotlin, consumindo serviços web da API de filmes TMDB, processando resposta com retrofit2, coroutines para tratamento de concorrência e integração ao Firebase Authentication para reconhecimento da identidade do usuário. Projetado com padrão de arquitetura MVVM com divisão de responsabilidades, separação de conceitos e desacoplamento de camadas.
*******
![MovieApp~1](https://user-images.githubusercontent.com/9430430/110244557-0b58c900-7f3e-11eb-829e-ff616c0b66cb.gif)
*******
![MovieApp~3](https://user-images.githubusercontent.com/9430430/110245436-e7978200-7f41-11eb-9dc6-c02495ac65ab.gif)
*******
![Movie_01](https://user-images.githubusercontent.com/9430430/110243198-1f99c780-7f38-11eb-9b66-4c11b21b9810.jpg)
*******
![Movie_02](https://user-images.githubusercontent.com/9430430/110243200-21638b00-7f38-11eb-9e4a-e05c58115940.jpg)
*******
![QRCODE](https://user-images.githubusercontent.com/9430430/110317195-8f688a80-7fea-11eb-812a-7befe286992c.jpg)
*******
### ESTRUTURA DOS PACOTES: MVVM ARCHITECTURE PATTERN
*******
### NETWORK LAYER
> **DATA**
*******
* :ballot_box_with_check:    **network:**
>Pacote onde ficam as classes de dados. Classes do retrofit e classes do response e os endpoint's da aplicação.
*******
* :ballot_box_with_check:   **model:**
> Pacote onde fica a classe responsável por listar os atributos para construir os itens da lista.
*******
* :ballot_box_with_check:   **response:**
> Pacote aonde ficam as classes de response(classes contendo anotaçõs do retrofit e moschi). 
  Essas camandas não misturamos com as camadas de apresentação.
*******
* RootResponse
> Classe response (Objeto que representa a raiz inicial da estrutura: responsável
              pelo mapeamento da estrutura de Json da API)
              
* DetailsResponse
> Classe response (responsável pelo mapeamento da estrutura de Json da API)      

* ResultsResponse
> Classe response (responsável pelo mapeamento da estrutura de Json da API)
  
* ThumbnailResponse
> Classe response (responsável pelo mapeamento da estrutura de Json da API)
*******
### APRESENTAÇÃO
> **PRESENTATION**
*******
> Pacote aonde ficam as activitys, view models e qualquer outra classe relacionada a apresentação.
  Toda que o usuário vizualiza e interage fica nesse pacote.
*******
* :ballot_box_with_check: **BaseActivity:**
 > Classe herdeira de AppCompatActivity() que configura uma toolbar padrão e foi 
             implementado também por meio dessa classe ações relacionadas ao menu na aplicação.
*******
* :ballot_box_with_check: **DetalhesActivity:**
> Classe da Activity Details responsável pela exibição de detalhes dos personagens selecionados.
*******
* :ballot_box_with_check: **Activity:**
> Classe principal (MainActivity) do projeto (features).

* :ballot_box_with_check: **Adapter:**
> Classe responsável por fazer a ligação do Array list para dentro do RecyclerView.

* :ballot_box_with_check: **ViewModel:**
> Classe responsável por obter os dados da camada de rede e passar esses dados para a activity/fragmetns fazendo a comunicação entre eles através da LiveData.
  A responsabilidade portanto do ViewModel é gerenciar os dados da Activity e fazer a ponte com as demais camadas do Aplicativo.
  
*******
### INSTRUÇÕES REFERENTES A CONFIGURAÇÃO
*******
**Requisitos:**

* :ballot_box_with_check: **1º Ter o Android Studio 4.1.1 Instalado na máquina e conexão com Internet**
* >https://developer.android.com/studio

* :ballot_box_with_check: **2º Cadastro API TMDB:**

Será necessário ter cadastro na API TMDB para obtenção das chaves de autentificação.
Para isso deverá fazer um cadastro e criação da conta no site oficial da API. Com essas chaves conseguimos fazer conexão com o servidor para obtenção dos dados.
Pois passaremos os parâmetros para a API e receberemos como resposta o retorno dos dados de resultado dessa requisição.

**API TMDB:**
> https://www.themoviedb.org/

* :ballot_box_with_check: **3º Configuração das chaves**

Com a chave em mãos, abra esse projeto no Android Studio, navegue até a pasta service e abra a classe ApiKey.kt e inclua sua chave obtidas no site da API TMDB.

* :ballot_box_with_check: **4º Configuração bibliotecas no build.grandle**
Abra o build.grandle (Module: app) e inclua as dependências específicas desse projeto e faça a sincronização "Sync Now"

    dependencies {

    implementation "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    implementation 'androidx.core:core-ktx:1.3.2'
    implementation 'androidx.appcompat:appcompat:1.2.0'
    implementation 'com.google.android.material:material:1.3.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'android.arch.lifecycle:extensions:1.1.1'

    //Lottie
    implementation 'com.airbnb.android:lottie:3.6.1'

    //Glide
    implementation "com.github.bumptech.glide:glide:$glideVersion"
    annotationProcessor "com.github.bumptech.glide:compiler:$glideVersion"

    //Slide intro
    implementation 'com.heinrichreimersoftware:material-intro:2.0.0'

    //Paging
    implementation 'androidx.paging:paging-runtime-ktx:2.1.2'

    //Squareup retrofit
    implementation "com.squareup.retrofit2:retrofit:$retrofitVersion"
    implementation "com.squareup.retrofit2:converter-moshi:$retrofitVersion"
    implementation "com.squareup.moshi:moshi-kotlin:$moshiVersion"
    kapt "com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion"

    //Coroutines
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_android_version"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_android_version"

    //RX Java
    implementation "io.reactivex.rxjava2:rxjava:$rxJava_version"
    implementation "io.reactivex.rxjava2:rxandroid:$rxJava_version"

    //Dagger2
    implementation "com.google.dagger:dagger-android:$dagger_Version"
    implementation "com.google.dagger:dagger-android-support:$dagger_Version"
    kapt "com.google.dagger:dagger-android-processor:$dagger_Version"

    // Import the Firebase BoM
    implementation platform('com.google.firebase:firebase-bom:26.3.0')

    // Declare the dependency for the Firebase SDK for Google Analytics
    implementation 'com.google.firebase:firebase-analytics-ktx'

    // For example, declare the dependencies for Firebase Authentication and Cloud Firestore
    implementation 'com.google.firebase:firebase-auth-ktx'
    implementation 'com.google.firebase:firebase-firestore-ktx'

    //Junit test
    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'

}
    
* :ballot_box_with_check: **5º Execução do projeto:**
Após todas as configurações iniciais das chaves e libs o projeto está pronto e pode ser executado normalmente. 

*******
### TEORIA
*******

* **A 1.1 - Ciclo de Vida de uma Activity**
> A Activity é um componente de aplicação com um ciclo de vida específico. Quando o usuário acessa a aplicação, navega pelas opções, sai ou retorna para a mesma, as atividades que a compõem passam por uma série de estados do ciclo de vida.

![image](https://user-images.githubusercontent.com/9430430/93096297-99808080-f67a-11ea-937b-e6ed514f7880.png)

*******
* **A 1.2 - Ciclo de Vida de um Fragment**

> Ao iniciar um fragment vários métodos que são chamados automaticamente pelo Sistema Operacional, e cada um desses são executados em um determinado momento.

> **O método onAttach** é o primeiro método chamado quando a fragment está sendo criada e é o responsável por “juntar" a Fragment com a Activity e receber como parâmetro a Activity em questão.

> **O próximo método chamado é o onCreate,** que é bem parecido com o onCreate da Activity, ou seja, ele cria a Fragment e, assim como na Activity, recebe um Bundle que pode conter alguma informação salva em execuções passadas. É importante notar que o esse método dentro da Fragment não acessa elementos de tela, ou seja não se pode fazer chamadas para elementos gráficos dentro desse método.

> **O próximo método é o onCreateView,** que é o local onde se deve fazer o contato com o layout da Fragment e carregá-lo. Lembre-se os elementos acessados aqui são apenas os elementos da Fragment. A assinatura desse método é um pouco maior, pois aqui se tem que inflar o XML para transformá-lo em um objeto do tipo View.

> **O próximo método é o onActivityCreated** é chamado apenas depois que a Activity já está criada (método onCreate da Activity já terminou), ou seja, aqui é o local onde se pode acessar ou modificar elementos de tela.

> **Os próximos métodos a serem invocados são onStart, onResume e onPause,** possuem a mesma funcionalidade do que quando chamados dentro de uma Activity.

> **O próximo método onSaveInstanceState** Este é muito importante se em sua aplicação é preciso salvar algum tipo de informação que deve ser utilizada futuramente: as informações podem ser salvas em um objeto Bundle que faz parte do parâmetro do método.

> **O método onStop** tem a mesma função do onStop da Activity.

> **O método onDestroyView** é chamado até ele ser executado ainda com a Fragment acessível, ou seja, sua hierarquia ainda pode ser acessada. Após a execução do método, a Fragment passa a não ser mais acessível, seu objeto ainda existe, mas não pode mais ser acessada.

> **Outro método chamado é o onDestroy** quando a Fragment não está mais sendo utilizada, mas o objeto ainda existe, pois ainda está ligada com a Activity.

> **Por fim, temos o método onDetach,** que é quando finalmente a Fragment não está mais ligada a nenhuma Activity, ou seja, não está mais associada a nada. 

![image](https://user-images.githubusercontent.com/9430430/93097317-cf723480-f67b-11ea-8686-544abaf8a688.png)

*******
* **A 2.2 - Arquitetura MVVM**
*******
![image](https://user-images.githubusercontent.com/9430430/93107632-41507b00-f688-11ea-8b75-7fa307dd900a.png)

**MVVM:** Tem como principal objetivo separar responsabilidades entre Views e Modelos
Aqui temos a View que responde somente para a ViewModel, e a ViewModel não comunica diretamente com a View. A ViewModel é então uma classe intermediaria entre a View e a Model que conecta uma com a outra fazendo assim intermediação entre elas através do mecanismo de conexão Data Binding.

**Modelo (Model):**
A Model caracteriza um conjunto de classes para descrever a lógica de negócios. Ela também descreve as regras de negócios para dados sobre como os dados podem ser manipulados ou alterados.

**Visão (View):**
A View representa componentes da interface do usuário. Ela também exibe os modelos na interface do usuário a partir do retorno da Presenter e da ViewModel. Assim como no MVP, a View aqui também tende a possuir o mínimo de regra de negócio possível, ela também é "burra", quem vai definir o que ela vai exibir é a ViewModel.

**ViewModel:**
A ViewModel é responsável por apresentar funções, métodos e comandos para manter o estado da View, operar a Model e ativar os eventos na View.
O ViewModel expõe fluxos de dados relevantes para o View. Mesmo neste caso, é a ponte entre o repositório e a View e contém toda a lógica de negócios.


### Principais benefícios da arquitetura MVVM
**Manutenção**
É possível entrar em partes menores e focadas do código e fazer alterações facilmente por causa da separação de diferentes tipos de códigos de maneira mais limpa. Isso ajudará a lançar novas versões rapidamente e a manter a agilidade.

**Testabilidade:**
No caso da arquitetura MVVM, cada parte do código permanece granular. Caso seja implementada da maneira adequada, todas as dependências internas e externas permanecerão do código que contém a lógica principal do aplicativo que você estava planejando testar.
Portanto, se você planeja escrever testes unitários com a lógica principal, fica muito mais fácil. Lembre-se de verificar se funciona bem quando escrito e continue executando, principalmente quando houver qualquer tipo de alteração no código, por menor que seja.

**Extensibilidade:**
Devido ao aumento de partes granulares de código e limites de separação, às vezes ele se mistura com a capacidade de manutenção. Se você planeja reutilizar qualquer uma dessas partes terá mais chances de fazê-lo.
Ele também vem com o recurso no qual você pode adicionar novos trechos de código ou substituir os existentes que executam trabalhos semelhantes em alguns locais da estrutura da arquitetura.
Sem dúvida, o principal objetivo de escolher a arquitetura MVVM é abstrair as Views para que o código por trás da lógica de negócios possa ser reduzido a uma extensão.

**Conclusão:** 
Para este projeto foi escolhido o padrão de Arquitetura MVVM justamente por fazer uso um padrão de divisão de responsabilidades, com separação de conceitos, e camadas diferentes, nele temos o desacoplamento da camada de "Network" da camada de "Apresentação", facilitando assim a organização, clareza e entendimento de cada parte do projeto, facilitando e  possibilitando o trabalho em diferentes frentes de camadas desacopladas em um projeto mais organizado e flexível.


