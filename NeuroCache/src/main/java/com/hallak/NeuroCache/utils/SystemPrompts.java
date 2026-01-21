package com.hallak.NeuroCache.utils;

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
                    Categoria: """;
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
                    
                    Retorne SOMENTE um JSON válido no formato:
                    
                    {
                      "confidence": 0.0
                    }
                    
                    Mensagem para avaliação:
                    """ + payload +"""
        """;
        }


        public static String getResponderPrompt () {
            return """
                    ...""";
        }


    }
