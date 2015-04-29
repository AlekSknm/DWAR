angular.module(‘myApp’)

    .service(‘UserService’,function($cookieStore){



    var user ={

        accessToken:«  »,

        id:«  »,

        isLogged:false,

        firstName:«  »,

        lastName:«  »,

        email:«  »,

        socialNetwork:«  »,

        image:«  »

    };



    // Fonctions utilisateurs

    this.setUser=function(userData){

        user.isLogged=true;

        // … Affecter les différents champs de notre utilisateur 

        $cookieStore.put(‘user’, user);// Ajouter dans les cookies

        sendToServer();

    }



    // Ajoutez d’autres fonctions … 

    this.sendToServer  =function(userData){

        // …

    }
 

   this.logout=function(){

        // Vider tous les champs

        user.isLogged=false;

        $cookieStore.remove(‘user’);

    }


});