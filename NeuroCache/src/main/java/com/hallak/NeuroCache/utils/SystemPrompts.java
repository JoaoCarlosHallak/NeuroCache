package com.hallak.NeuroCache.utils;

import com.hallak.NeuroCache.entities.Memory;

import java.util.List;

public class SystemPrompts {

    public static String getAnalyzerPromptDomain(String payload) {
        return """
                    Atue como um classificador de texto estrito (JSON mode off).
                    
                    Suas Categorias Permitidas:
                    [RELATIONSHIP, SPIRITUALITY, HEALTH, GENERIC]
                    
                    Regras Absolutas:
                    1. Responda APENAS com uma das palavras da lista acima.
                    2. NÃO explique sua decisão.
                    3. NÃO use pontuação, parênteses ou texto extra.
                    4. Forma muito proibida: DOMINIO (alguma justificativa)
                    
                    ### Exemplos de Aprendizado (Few-Shot):
                    
                    Mensagem: "Como faço para ele gostar de mim?"
                    Categoria: RELATIONSHIP
                    
                    Mensagem: "Qual o sentido da vida e do universo?"
                    Categoria: SPIRITUALITY
                    
                    Mensagem: "Doi meu joelho quando corro."
                    Categoria: HEALTH
                    
                    Mensagem: "Vai chover hoje?"
                    Categoria: GENERIC
                    
                    Mensagem: "Eu sou feio?"
                    Categoria: SPIRITUALITY
                    
                    ### Sua vez:
                    
                    Mensagem: """ + payload + """   
                    Categoria:""";
        }

        public static String getAnalyzerPromptConfidence(String payload) {
            return """
                    Você é um sistema de análise semântica.
                    
                    Sua tarefa é avaliar o TEXTO fornecido e atribuir um score de CONFIDENCE,
                    indicando o quão estável, recorrente e confiável o conteúdo é para ser
                    armazenado como memória de longo prazo.
                    
                    Considere:
                    - Se o texto descreve um padrão recorrente ou traço consistente.
                    - Se o texto parece temporário, emocional ou circunstancial.
                    - Se há termos que indicam frequência (ex: "sempre", "costuma", "geralmente").
                    - Se o texto descreve um fato ou apenas uma impressão momentânea.
                    
                    Escala de confidence:
                    - 0.0 a 0.4 → evento pontual, emoção, suposição ou momento isolado
                    - 0.5 a 0.7 → informação possivelmente relevante, mas ainda instável
                    - 0.8 a 1.0 → padrão claro, recorrente e estável
                    
                    Regras obrigatórias:
                    - NÃO interprete além do texto.
                    - NÃO invente contexto.
                    - NÃO responda com explicações longas.
                    - NÃO emita opiniões.
                    - Avalie apenas o que está explicitamente escrito.
                    
                    Retorne SOMENTE o valor double
                    
                    Ex:
                    
                    0.0
                 
                    
                    
                    Mensagem para avaliação:
                    """ + payload +"""
        """;
        }




    public static String getContextPayloadPrompt(String payload) {
        return """
Você é um classificador de mensagens para um sistema de memória.

Sua função é decidir se um texto deve ser:
- armazenado como memória
- usado para responder uma pergunta
- ou ambos

Classifique o texto entre <TEXT> e </TEXT> em UMA das categorias:

INFO
MIXED
QUEST

Definições:

INFO
O texto é uma afirmação clara e declarativa de fatos, crenças, identidade, preferências ou estados do usuário.
Deve ser algo que possa ser armazenado como dado relativamente estável.

QUEST
O texto tem como objetivo principal obter uma resposta, explicação, ajuda ou validação.
Inclui perguntas diretas ou indiretas, inclusive perguntas sobre si mesmo.

MIXED
O texto contém simultaneamente:
- uma afirmação factual clara e armazenável
e
- uma pergunta separada ou pedido explícito.

Regras importantes:

1. Se o texto terminar com ponto de interrogação e estiver buscando validação, confirmação ou julgamento, classifique como QUEST.
2. Perguntas sobre si mesmo NÃO são consideradas INFO.
3. Só classifique como MIXED se houver claramente uma afirmação independente e uma pergunta distinta.
4. Retorne SOMENTE uma palavra: INFO, MIXED ou QUEST.
5. Não escreva mais nada.

<TEXT>
""" + payload + """
</TEXT>
""";
    }

    public static String getResponderQuestPrompt(String payload, List<Memory> memoryList) {

        String memoriesSection;

        if (memoryList == null || memoryList.isEmpty()) {
            memoriesSection = "[]";
        } else {
            StringBuilder sb = new StringBuilder();
            for (Memory memory : memoryList) {
                sb.append("- ")
                        .append(memory.getContent())
                        .append("\n");
            }
            memoriesSection = sb.toString();
        }

        return """
            Você é um assistente inteligente com capacidade de contextualização personalizada.

            Você receberá dois elementos:
            1) Uma PERGUNTA (sempre presente).
            2) Uma lista opcional de MEMÓRIAS do usuário (pode estar vazia).

            Sua tarefa:
            - Sempre responder à pergunta de forma clara, estruturada e útil.
            - Se houver memórias disponíveis, utilize-as para enriquecer a resposta, tornando-a mais personalizada.
            - Se não houver memórias, responda normalmente, de forma útil e objetiva.
            - Nunca mencione explicitamente que está utilizando memórias.
            - Nunca invente memórias que não estejam listadas.
            - Se as memórias não forem relevantes para a pergunta, ignore-as.
            - Priorize clareza, coerência e aplicabilidade prática.
            - Não inclua metacomentários.

            ENTRADA:

            PERGUNTA:
            """ + payload + """

            MEMÓRIAS:
            """ + memoriesSection;
    }



    }
