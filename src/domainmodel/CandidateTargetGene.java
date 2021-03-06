package domainmodel;


public class CandidateTargetGene implements Comparable<CandidateTargetGene> {
    private final GeneIdentifier geneID;
    private final int rank;
    private final int numberOfMotifsOrTracks;

    public CandidateTargetGene(final GeneIdentifier geneID, final int rank) {
        this(geneID, rank, 1);
    }

    public CandidateTargetGene(final GeneIdentifier geneID, final int rank, final int numberOfMotifsOrTracks) {
        if (geneID == null) throw new IllegalArgumentException();
        this.geneID = geneID;
        this.rank = rank;
        this.numberOfMotifsOrTracks = numberOfMotifsOrTracks;
    }

    public GeneIdentifier getGeneID() {
        return this.geneID;
    }

    public String getGeneName() {
        return this.geneID.getGeneName();
    }

    public SpeciesNomenclature getSpeciesNomenclature() {
        return this.geneID.getSpeciesNomenclature();
    }

    public int getRank() {
        return this.rank;
    }

    public int getNumberOfMotifsOrTracks() {
        return numberOfMotifsOrTracks;
    }

    @Override
    public int compareTo(CandidateTargetGene other) {
        int r = new Integer(getRank()).compareTo(other.getRank());
        if (r != 0) return r;
        return getGeneName().compareToIgnoreCase(other.getGeneName());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CandidateTargetGene that = (CandidateTargetGene) o;

        if (numberOfMotifsOrTracks != that.numberOfMotifsOrTracks) return false;
        if (rank != that.rank) return false;
        if (!geneID.equals(that.geneID)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = geneID.hashCode();
        result = 31 * result + rank;
        result = 31 * result + numberOfMotifsOrTracks;
        return result;
    }
}
