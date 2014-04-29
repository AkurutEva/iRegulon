package view.resultspanel.trackview;

import cytoscape.Cytoscape;
import domainmodel.AbstractTrack;
import domainmodel.TranscriptionFactor;
import view.resultspanel.trackview.detailpanel.TFandTrackSelected;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public final class Track2TFDetailFrame extends JDialog {
    private final TFandTrackSelected information;

    private static final String[] PREFIXES = new String[]{"A", "B", "C", "D"};

    public Track2TFDetailFrame(final TFandTrackSelected information) {
        super(Cytoscape.getDesktop(), true);

        this.information = information;

        setTitle(deriveTitle());
        setContentPane(new JPanel(new BorderLayout()) {
            {
                add(createContentPanel(), BorderLayout.CENTER);
                add(new JPanel(new FlowLayout()) {
                    {
                        add(new JButton(new CloseAction()));
                    }
                }, BorderLayout.SOUTH);
            }
        });
        pack();
    }

    public TFandTrackSelected getInformation() {
        return information;
    }

    private String deriveTitle() {
        final StringBuilder builder = new StringBuilder();
        builder.append("Detail on relation between ");
        builder.append(information.getTrack().getName());
        builder.append(" and ");
        builder.append(information.getTranscriptionFactor().getName());
        return builder.toString();
    }

    private JComponent createContentPanel() {
        final JPanel content = new JPanel(new GridBagLayout());
        final GridBagConstraints cc = new GridBagConstraints();
        cc.anchor = GridBagConstraints.CENTER;
        cc.fill = GridBagConstraints.BOTH;
        cc.gridheight = 1;
        cc.gridwidth = 1;
        cc.weightx = 1.0;
        cc.weighty = 1.0;
        cc.gridx = 0;
        cc.gridy = 0;

        String prevComponent = "Enriched track";
        content.add(createEnrichedTrackPanel(cc.gridy, getInformation().getTrack()), cc);
        cc.gridy++;
        /* FIXES
        if (isMotifSimilarityUsed(getInformation().getTranscriptionFactor())) {
            content.add(createSimilarMotifPanel(cc.gridy, getInformation().getTranscriptionFactor()), cc);
            prevComponent = "Similar motif";
            cc.gridy++;
        }
        if (isOrthologousGeneUsed(getInformation().getTranscriptionFactor())) {
            content.add(createOrthologousGenePanel(cc.gridy, getInformation().getTranscriptionFactor()), cc);
            prevComponent = "Orthologous gene";
            cc.gridy++;
        }    */
        content.add(createTranscriptionFactorPanel(cc.gridy, prevComponent, getInformation().getTrack(), getInformation().getTranscriptionFactor()), cc);

        return new JScrollPane(content, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    }
    /*  FIXES
    private boolean isMotifSimilarityUsed(final TranscriptionFactor factor) {
        return !Float.isNaN(factor.getMaxMotifSimilarityFDR()) && factor.getSimilarMotifName() != null;
    }

    private boolean isOrthologousGeneUsed(final TranscriptionFactor factor) {
        return !Float.isNaN(factor.getMinOrthologousIdentity()) && factor.getOrthologousGeneName() != null;
    }
    */
    private JPanel createEnrichedTrackPanel(final int idx, final AbstractTrack track) {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints cc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEtchedBorder());

        addTitle(idx, "Enriched track", cc, panel);
        addLabel("Name", track.getName(), cc, panel);
        addLabel("Description", track.getDescription(), cc, panel);
        addLabel("NEScore", track.getNEScore(), cc, panel);
        //addLogo(track.getName(), cc, panel);

        return panel;
    }

    /*
    private JPanel createSimilarMotifPanel(final int idx, final TranscriptionFactor factor) {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints cc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEtchedBorder());

        addTitle(idx, "Similar to motif", cc, panel);
        addLabel("Name", factor.getSimilarMotifName(), cc, panel);
        addLabel("Description", factor.getSimilarMotifDescription(), cc, panel);
        addLabel("Similarity (FDR)", factor.getMaxMotifSimilarityFDR(), cc, panel);
        addLogo(factor.getSimilarMotifName(), cc, panel);

        return panel;
    }

    private JPanel createOrthologousGenePanel(final int idx, final TranscriptionFactor factor) {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints cc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEtchedBorder());

        addTitle(idx, "Annotated for gene", cc, panel);
        addLabel("Name", factor.getOrthologousGeneName(), cc, panel);
        addLabel("Species", factor.getOrthologousSpecies(), cc, panel);
        addLabel("Identity (fraction)", factor.getMinOrthologousIdentity(), cc, panel);
        addVerticalSpanner(cc, panel);

        return panel;
    }
    */

    private JPanel createTranscriptionFactorPanel(final int idx, final String prevComponent, final AbstractTrack track, final TranscriptionFactor factor) {
        final JPanel panel = new JPanel(new GridBagLayout());
        final GridBagConstraints cc = new GridBagConstraints();
        panel.setBorder(BorderFactory.createEtchedBorder());

        if (prevComponent.equals("Enriched track") || prevComponent.equals("Similar track"))
            addTitle(idx, "Annotated for transcription factor", cc, panel);
        else addTitle(idx, "Orthologous to transcription factor", cc, panel);
        addLabel("Name", factor.getName(), cc, panel);
        addLabel("Species and nomenclature", factor.getSpeciesNomeclature().toString(), cc, panel);

//        cc.anchor = GridBagConstraints.LINE_START;
//        cc.fill = GridBagConstraints.NONE;
//        cc.weightx = 0.0; cc.weighty = 0.0;
//        cc.gridwidth = 2; cc.gridheight = 1;
//        cc.gridx = 0;

//        final JLabel labelLB = new JLabel("Target genes:");
//        panel.add(labelLB, cc);
//
//        cc.gridy++;
//
//        cc.anchor = GridBagConstraints.CENTER;
//        cc.fill = GridBagConstraints.BOTH;
//        cc.weightx = 1.0; cc.weighty = 1.0;
//        cc.gridwidth = 2; cc.gridheight = 1;
//        cc.gridx = 0;
//
//        final JScrollPane pane = new JScrollPane(new JTable(new CandidateTargetGeneTableModel(motif)));
//        final ImageIcon icon = LogoUtilities.createImageIcon(motif.getName());
//        pane.setMaximumSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
//		panel.add(pane, cc);

        return panel;
    }

    private void addTitle(final int idx, final String title, final GridBagConstraints cc, final JComponent container) {
        cc.anchor = GridBagConstraints.CENTER;
        cc.fill = GridBagConstraints.HORIZONTAL;
        cc.weightx = 1.0;
        cc.weighty = 0.0;
        cc.gridwidth = 2;
        cc.gridheight = 1;
        cc.gridx = 0;

        final JLabel titleLB = new JLabel(PREFIXES[idx] + ". " + title);
        titleLB.setFont(new Font("Serif", 0, 30));
        container.add(titleLB, cc);

        cc.gridy += 2;
    }

    private void addLabel(final String label, final Object value, final GridBagConstraints cc, final JComponent container) {
        cc.anchor = GridBagConstraints.LINE_START;
        cc.fill = GridBagConstraints.NONE;
        cc.weightx = 0.0;
        cc.weighty = 0.0;
        cc.gridwidth = 1;
        cc.gridheight = 1;
        cc.gridx = 0;

        final JLabel labelLB = new JLabel(label + ":");
        container.add(labelLB, cc);

        cc.anchor = GridBagConstraints.CENTER;
        cc.fill = GridBagConstraints.HORIZONTAL;
        cc.weightx = 1.0;
        cc.weighty = 0.0;
        cc.gridwidth = 1;
        cc.gridheight = 1;
        cc.gridx = 1;

        final JTextField fieldTF = new JTextField(value == null ? "" : value.toString());
        fieldTF.setEditable(false);
        container.add(fieldTF, cc);

        cc.gridy++;
    }

    /* FIXES
    private void addLogo(final String motifName, final GridBagConstraints cc, final JComponent container) {
        cc.anchor = GridBagConstraints.CENTER;
        cc.fill = GridBagConstraints.BOTH;
        cc.weightx = 1.0;
        cc.weighty = 1.0;
        cc.gridwidth = 2;
        cc.gridheight = 1;
        cc.gridx = 0;

        final JLabel logoLabel = new JLabel();
        logoLabel.setHorizontalAlignment(JLabel.CENTER);

        final ImageIcon logoIcon = LogoUtilities.createImageIcon(motifName);
        if (logoIcon != null) {
            logoLabel.setIcon(logoIcon);
            logoLabel.addMouseListener(new LogoMouseListener(motifName));
        } else {
            logoLabel.setText("<html><br><i>This motif cannot be shown as it is part of TRANSFAC Pro.</i><br><br></html>");
        }

        container.add(logoLabel, cc);

        cc.gridy++;
    }

    private void addVerticalSpanner(final GridBagConstraints cc, final JComponent container) {
        cc.anchor = GridBagConstraints.CENTER;
        cc.fill = GridBagConstraints.BOTH;
        cc.weightx = 1.0;
        cc.weighty = 1.0;
        cc.gridwidth = 2;
        cc.gridheight = 1;
        cc.gridx = 0;

        container.add(Box.createGlue(), cc);
    }
    */

    private class CloseAction extends AbstractAction {
        private CloseAction() {
            super("Close");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            dispose();
        }
    }
}