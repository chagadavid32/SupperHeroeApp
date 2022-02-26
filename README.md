# SupperHeroeApp

Aplicacion android nativa que muestra informacion de personajes de comics Marvel. 

La aplicacion inicialmente consume un servicio web de marvel para mostrar informacion de personajes.
Al seleccionar un personaje, se muestra un ViewPager que a su vez mostrará los comics, eventos y series de comics en los cuales el personaje hace aparición.

La aplicacion usa principalmente Fragmentos, RecyclerViews y un componente Navigation. La arquitectura usada es MVVM y el consumo de los servicios web se realiza mediante Retrofit, que a su vez se generan instancias usando patrón Builder 
