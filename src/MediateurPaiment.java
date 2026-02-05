/**
 * Cette interface permet de déterminer la stratégie du médiateur
 * @author Arnaud Jean
 * @since Hiver 2026
 * @version 1.0
 */
public interface MediateurPaiment {
    void notifier(Object composant, String evenement);
}