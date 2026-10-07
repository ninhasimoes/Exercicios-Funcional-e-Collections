package exercicio04;

/*
 * Por que uma interface própria e não um Predicate<Cadastro>?
 * O Predicate só responde SIM/NÃO (boolean): ele diz que o cadastro é inválido, mas não diz POR QUÊ.
 * Aqui a gente precisa devolver a mensagem de erro de cada regra violada, então o retorno
 * precisa ser uma String (null = válido). Como o Predicate.test() sempre devolve boolean,
 * não dá pra carregar a mensagem nele. Daí a interface funcional Regra, com um único método abstrato.
 */
@FunctionalInterface
public interface Regra {
    String validar(Cadastro c);   // devolve null quando válido ou a mensagem de erro
}
