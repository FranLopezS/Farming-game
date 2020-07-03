<helpset version="1.0">
   <title>Ayuda Huerto</title>
   <maps>
      <!-- Pagina por defecto al mostrar la ayuda -->
      <homeID>index</homeID>
      <!-- Que mapa deseamos -->
      <mapref location="map.jhm"/>
   </maps>

   <!-- Las Vistas que deseamos mostrar en la ayuda -->
   <!-- La tabla de contenidos -->
   <view>
      <name>Tabla Contenidos</name>
      <label>Tabla de contenidos</label>
      <type>javax.help.TOCView</type>
      <data>toc.xml</data>
   </view>

   <!-- El indice -->
   <view>
      <name>Indice</name>
      <label>El indice</label>
      <type>javax.help.IndexView</type>
      <data>index.xml</data>
   </view>

   <!-- La pestana de busqueda -->
   <view>
      <name>Buscar</name>
      <label>Buscar</label>
      <type>javax.help.SearchView</type>
      <!-- Motor de busqueda que utilizamos -->
      <data engine="com.sun.java.help.search.DefaultSearchEngine">
         <!-- Carpeta donde se genera la indexacion de nuestra ayuda -->
         JavaHelpSearch
      </data>
   </view>

</helpset>
