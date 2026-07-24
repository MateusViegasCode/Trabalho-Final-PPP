package quiz.aplicacao.programacao;

import quiz.framework.display.Exibicao;
import quiz.framework.core.Pergunta;
import quiz.framework.core.QuizResult;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// Exibição do quiz usando Java Swing
public class ExibicaoSwingProgramacao implements Exibicao {

    private static final String FONT_FAMILY = "Segoe UI";
    private static final Color FRAME_BACKGROUND = new Color(245, 246, 248);
    private static final Color SURFACE_COLOR = Color.WHITE;
    private static final Color BORDER_COLOR = new Color(216, 220, 224);
    private static final Color ACCENT_COLOR = new Color(43, 103, 206);
    private static final Color ACCENT_HOVER_COLOR = new Color(33, 88, 183);
    private static final Color TEXT_COLOR = new Color(33, 37, 41);
    private static final Color MUTED_TEXT_COLOR = new Color(94, 104, 117);
    
    private JFrame frame;
    private JTextArea questionArea;
    private JPanel optionsPanel;
    private ButtonGroup optionsGroup;
    private RoundedActionButton confirmButton;
    private JTextArea messageArea;
    private JLabel scoreLabel;
    private int selectedAnswer;
    private boolean answerConfirmed;
    
    public ExibicaoSwingProgramacao() {
        this.selectedAnswer = -1;
        this.answerConfirmed = false;
        initializeGUI();
    }
    
    private void initializeGUI() {
        UIManager.put("Button.font", new Font(FONT_FAMILY, Font.PLAIN, 14));
        UIManager.put("Label.font", new Font(FONT_FAMILY, Font.PLAIN, 14));
        UIManager.put("RadioButton.font", new Font(FONT_FAMILY, Font.PLAIN, 14));

        // Frame principal
        frame = new JFrame("Quiz de Programação");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(960, 680);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.getContentPane().setBackground(FRAME_BACKGROUND);
        
        // Container principal
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(FRAME_BACKGROUND);
        
        // Mensagens
        JPanel topPanel = new JPanel(new BorderLayout(0, 8));
        topPanel.setBackground(FRAME_BACKGROUND);
        messageArea = new JTextArea(2, 60);
        messageArea.setEditable(false);
        messageArea.setFocusable(false);
        messageArea.setRequestFocusEnabled(false);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setBackground(FRAME_BACKGROUND);
        messageArea.setForeground(MUTED_TEXT_COLOR);
        messageArea.setFont(new Font(FONT_FAMILY, Font.PLAIN, 13));
        messageArea.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        messageArea.setOpaque(false);
        messageArea.setCaretColor(FRAME_BACKGROUND);
        JScrollPane messageScrollPane = new JScrollPane(messageArea);
        messageScrollPane.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR),
            BorderFactory.createEmptyBorder(2, 2, 2, 2)));
        messageScrollPane.setBackground(SURFACE_COLOR);
        messageScrollPane.getViewport().setBackground(SURFACE_COLOR);
        messageScrollPane.setPreferredSize(new Dimension(0, 70));
        topPanel.add(messageScrollPane, BorderLayout.CENTER);
        
        scoreLabel = new JLabel("Pontuação: 0 | Acertos: 0 | Erros: 0");
        scoreLabel.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
        scoreLabel.setForeground(TEXT_COLOR);
        topPanel.add(scoreLabel, BorderLayout.SOUTH);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        // Pergunta e Alternativas
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_COLOR),
            BorderFactory.createEmptyBorder(18, 18, 18, 18)));
        centerPanel.setBackground(SURFACE_COLOR);
        
        // Pergunta
        questionArea = new JTextArea();
        questionArea.setEditable(false);
        questionArea.setFocusable(false);
        questionArea.setRequestFocusEnabled(false);
        questionArea.setOpaque(false);
        questionArea.setLineWrap(true);
        questionArea.setWrapStyleWord(true);
        questionArea.setFont(new Font(FONT_FAMILY, Font.BOLD, 18));
        questionArea.setForeground(TEXT_COLOR);
        questionArea.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        questionArea.setCaretColor(SURFACE_COLOR);
        JScrollPane questionScrollPane = new JScrollPane(questionArea);
        questionScrollPane.setBorder(BorderFactory.createEmptyBorder());
        questionScrollPane.setOpaque(false);
        questionScrollPane.getViewport().setOpaque(false);
        questionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        questionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        questionScrollPane.setPreferredSize(new Dimension(0, 140));
        questionScrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, 160));
        questionScrollPane.setAlignmentX(Component.LEFT_ALIGNMENT);
        centerPanel.add(questionScrollPane);
        centerPanel.add(Box.createVerticalStrut(20));
        
        // Alternativas
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        optionsPanel.setBackground(SURFACE_COLOR);
        optionsGroup = new ButtonGroup();
        
        centerPanel.add(optionsPanel);
        centerPanel.add(Box.createVerticalGlue());
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        // Botão Confirmar
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        bottomPanel.setBackground(FRAME_BACKGROUND);
        
        confirmButton = new RoundedActionButton("Confirmar Resposta");
        stylePrimaryButton(confirmButton);
        confirmButton.setPreferredSize(new Dimension(230, 48));
        confirmButton.addActionListener(e -> confirmAnswer());
        
        bottomPanel.add(confirmButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        // Adicionar ao frame
        frame.add(mainPanel);
        frame.setVisible(true);
    }
    
    @Override
    public void showQuestion(Pergunta question) {
        // Limpar seleção anterior
        selectedAnswer = -1;
        answerConfirmed = false;
        optionsGroup.clearSelection();
        
        // Exibir pergunta
        questionArea.setText(question.getStatement());
        questionArea.setCaretPosition(0);
        
        // Limpar painel de opções
        optionsPanel.removeAll();
        optionsGroup = new ButtonGroup();
        
        // Adicionar radio buttons para cada alternativa
        java.util.List<String> alternatives = question.getAlternatives();
        for (int i = 0; i < alternatives.size(); i++) {
            final int index = i;
            RoundedChoiceButton radioButton = new RoundedChoiceButton(alternatives.get(i));
            radioButton.setFont(new Font(FONT_FAMILY, Font.PLAIN, 14));
            radioButton.setForeground(TEXT_COLOR);
            radioButton.setAlignmentX(Component.LEFT_ALIGNMENT);
            radioButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            radioButton.setIconTextGap(12);
            radioButton.addActionListener(e -> selectedAnswer = index);
            
            optionsGroup.add(radioButton);
            optionsPanel.add(radioButton);
            optionsPanel.add(Box.createVerticalStrut(12));
        }
        
        optionsPanel.revalidate();
        optionsPanel.repaint();
        
        confirmButton.setEnabled(true);
        confirmButton.setText("Confirmar Resposta");
    }
    
    @Override
    public int getUserAnswer() {
        // Aguardar até que o usuário confirme
        answerConfirmed = false;
        while (!answerConfirmed) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
                Thread.currentThread().interrupt();
            }
        }
        return selectedAnswer;
    }
    
    private void confirmAnswer() {
        if (selectedAnswer >= 0) {
            answerConfirmed = true;
            confirmButton.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(frame, 
                "Selecione uma alternativa antes de confirmar!",
                "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    @Override
    public void showResult(QuizResult result) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("RESULTADO FINAL\n");
        sb.append("\n");
        sb.append("Acertos: ").append(result.getHits()).append("\n");
        sb.append("Erros: ").append(result.getErrors()).append("\n");
        sb.append("Total de perguntas: ").append(result.getTotalQuestions()).append("\n");
        sb.append("Pontuação final: ").append(result.getScore()).append("\n");
        sb.append(String.format("Taxa de acerto: %.1f%%%n", result.getAccuracyPercentage()));
        sb.append("\n");
        
        messageArea.setText(sb.toString());
        messageArea.setCaretPosition(0);
        
        // Mostrar também em dialog
        JOptionPane.showMessageDialog(frame,
            "Quiz finalizado!\n\n" +
            "Acertos: " + result.getHits() + "\n" +
            "Erros: " + result.getErrors() + "\n" +
            "Pontuação: " + result.getScore() + "\n" +
            String.format("Taxa de acerto: %.1f%%", result.getAccuracyPercentage()),
            "Resultado Final",
            JOptionPane.INFORMATION_MESSAGE);
        
        confirmButton.setText("Fechar");
        confirmButton.setEnabled(true);
    }
    
    @Override
    public void showMessage(String message) {
        messageArea.setText(message);
        messageArea.setCaretPosition(0);
    }
    
    @Override
    public void waitForNext() {
        // avança sozinho pra não exigir um segundo clique do usuário
        confirmButton.setEnabled(false);
        try {
            Thread.sleep(700);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
    
    @Override
    public void clear() {
        selectedAnswer = -1;
        optionsGroup.clearSelection();
    }
    
    @Override
    public void updateStats(int score, int hits, int errors) {
        SwingUtilities.invokeLater(() -> scoreLabel.setText(String.format(
            "Pontuação: %d | Acertos: %d | Erros: %d",
            score, hits, errors
        )));
    }

    private void stylePrimaryButton(JButton button) {
        button.setFont(new Font(FONT_FAMILY, Font.BOLD, 14));
        button.setBackground(ACCENT_COLOR);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    private static final class RoundedActionButton extends JButton {

        private static final int ARC = 28;

        RoundedActionButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder(12, 22, 12, 22));
            setRolloverEnabled(true);
            setForeground(Color.WHITE);
            setBackground(ACCENT_COLOR);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (isEnabled()) {
                        setBackground(ACCENT_HOVER_COLOR);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (isEnabled()) {
                        setBackground(ACCENT_COLOR);
                    }
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC, ARC);
            g2.setColor(ACCENT_COLOR.darker());
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC, ARC);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    private static final class RoundedChoiceButton extends JToggleButton {

        private static final int ARC = 24;

        RoundedChoiceButton(String text) {
            super(text);
            setOpaque(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder(12, 18, 12, 18));
            setRolloverEnabled(true);
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            setHorizontalAlignment(SwingConstants.LEFT);
            setUI(new BasicButtonUI());
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            Color fill = SURFACE_COLOR;
            Color border = BORDER_COLOR;
            if (isSelected()) {
                fill = new Color(232, 240, 255);
                border = ACCENT_COLOR;
            } else if (getModel().isRollover()) {
                fill = new Color(247, 249, 251);
            }

            g2.setColor(fill);
            g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC, ARC);
            g2.setColor(border);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, ARC, ARC);
            g2.dispose();

            super.paintComponent(g);
        }
    }
}
