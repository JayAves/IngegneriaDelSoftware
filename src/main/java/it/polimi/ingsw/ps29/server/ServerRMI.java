package it.polimi.ingsw.ps29.server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

public interface ServerRMI extends Remote {
	




/*Dichiara un’interfaccia (coi metodi che devono essere richiamabili da remoto) che estende java.rmi.Remote
E' necessario che i metodi dichiarino di lanciare l’eccezione `java.rmi.RemoteException
Definisce una classe che implementa tale interfaccia ed estende java.rmi.server.UnicastRemoteObject (es. public class RemoteHelloImpl extends UnicastRemoteObject
implements RemoteHello{ )
Utilizza il compilatore rmic che genera stub e skeleton
Crea un’instanza dell’oggetto e la registra presso il
rmiregistry
Prima deve esser già stato lanciato l’rmiregistry e deve essere in grado di trovare le classi degli oggetti che vengono registrati
*/
}
